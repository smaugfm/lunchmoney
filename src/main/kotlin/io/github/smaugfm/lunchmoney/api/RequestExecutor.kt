package io.github.smaugfm.lunchmoney.api

import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiRequestException
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.LunchmoneyAbstractApiRequest
import io.github.smaugfm.lunchmoney.response.ApiErrorResponse
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpHeaderValues
import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.HttpMethod
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.serializer
import mu.KotlinLogging
import reactor.core.publisher.Mono
import reactor.netty.ByteBufMono
import reactor.netty.http.client.HttpClient
import reactor.netty.http.client.HttpClientResponse
import java.io.ByteArrayOutputStream
import java.io.IOException

val log = KotlinLogging.logger { }

internal class RequestExecutor(
    token: String,
    val json: Json,
    private val port: Int = 443,
    private val httpClient: HttpClient
) {
    private val authorizationHeader = "Bearer $token"

    fun <R, T, A : LunchmoneyAbstractApiRequest<R, T>> execute(
        requestMono: Mono<A>,
        responseSerializer: KSerializer<R>,
        paramsSerializer: KSerializer<T>
    ): Mono<R> = requestMono
        .flatMap { request: A ->
            val body = requestBodyToByteBuffer(paramsSerializer, request.body())
            httpClient
                .port(port)
                .headers { addHeaders(request.method(), it) }
                .request(request.method())
                .uri(request.pathAndQuery())
                .send(Mono.just(body))
                .responseSingle { resp, byteBufMono ->
                    processResponse(resp, byteBufMono, responseSerializer)
                }
        }

    private fun addHeaders(
        method: HttpMethod,
        h: HttpHeaders
    ) {
        h.add(HttpHeaderNames.AUTHORIZATION, authorizationHeader)
        if (method == HttpMethod.PUT || method == HttpMethod.POST) {
            h.add(
                HttpHeaderNames.CONTENT_TYPE,
                HttpHeaderValues.APPLICATION_JSON
            )
        }
    }

    private fun <R> processResponse(
        resp: HttpClientResponse,
        byteBufMono: ByteBufMono,
        serializer: KSerializer<R>
    ): Mono<R> {
        val statusCode = resp.status().code()
        return byteBufMono
            .asString()
            .flatMap { body: String ->
                try {
                    Mono.justOrEmpty(
                        deserializeResponseBody(
                            serializer,
                            statusCode,
                            body
                        )
                    ).switchIfEmpty(
                        if (isOkResponse(resp)) {
                            Mono.empty()
                        } else {
                            Mono.error(
                                LunchmoneyApiResponseException(
                                    body,
                                    null,
                                    statusCode
                                )
                            )
                        }
                    )
                } catch (e: LunchmoneyApiResponseException) {
                    Mono.error(e)
                } catch (error: IOException) {
                    Mono.error(
                        LunchmoneyApiResponseException(
                            body,
                            error,
                            statusCode
                        )
                    )
                }
            }
    }

    private fun <R> deserializeResponseBody(
        serializer: KSerializer<R>,
        status: Int,
        body: String
    ): R? =
        try {
            doDeserialize(serializer, body)
        } catch (e: SerializationException) {
            val error = deserializeApiError(body)
            if (error != null) {
                throw error.toException(body, status)
            } else {
                throw e
            }
        }

    private fun deserializeApiError(body: String): ApiErrorResponse? =
        try {
            doDeserialize<ApiErrorResponse>(
                json.serializersModule.serializer(),
                body
            )
        } catch (other: SerializationException) {
            log.warn(other) { "Unknown error response" }
            null
        }

    private fun <T> doDeserialize(serializer: KSerializer<T>, body: String): T =
        json.decodeFromString(serializer, body)

    private fun <T> serializeRequestBody(serializer: KSerializer<T>, body: T): ByteArray {
        val os = ByteArrayOutputStream()
        json.encodeToStream(serializer, body, os)
        return os.toByteArray()
    }

    private fun <T> requestBodyToByteBuffer(serializer: KSerializer<T>, body: T?): ByteBuf {
        if (body == null)
            return Unpooled.EMPTY_BUFFER
        return try {
            val res: ByteArray = serializeRequestBody(serializer, body)
            Unpooled.wrappedBuffer(res)
        } catch (e: IOException) {
            throw LunchmoneyApiRequestException(e)
        }
    }

    private fun isOkResponse(response: HttpClientResponse) =
        response.status().codeAsText().startsWith("2")
}
