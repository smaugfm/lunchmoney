package com.github.smaugfm.lunchmoney

import com.github.smaugfm.lunchmoney.exception.ApiRequestException
import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
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
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.serializer
import reactor.core.publisher.Mono
import reactor.netty.ByteBufMono
import reactor.netty.http.client.HttpClient
import reactor.netty.http.client.HttpClientResponse
import reactor.netty.resources.ConnectionProvider
import java.io.ByteArrayOutputStream
import java.io.IOException

@OptIn(ExperimentalSerializationApi::class)
open class Lunchmoney protected constructor(
    token: String,
    baseUrl: String = "https://dev.lunchmoney.app/v1",
    private val port: Int = 443,
    jsonBuilderAction: JsonBuilder.() -> Unit = {
        namingStrategy = JsonNamingStrategy.SnakeCase
    },
    reactorNettyConnectionProvider: ConnectionProvider? = null
) {
    private val httpClient: HttpClient
    private val authorizationHeader = "Bearer $token"
    val json = Json(builderAction = jsonBuilderAction)

    init {
        httpClient =
            if (reactorNettyConnectionProvider != null) {
                HttpClient.create(reactorNettyConnectionProvider).baseUrl(baseUrl)
            } else {
                HttpClient.create().baseUrl(baseUrl)
            }
    }

    inline fun <reified R, reified T, A : ApiRequest<R, T>> execute(request: A): Mono<R> =
        execute<R, T, A>(Mono.just(request))

    inline fun <reified R, reified T, A : ApiRequest<R, T>> execute(requestMono: Mono<A>): Mono<R> =
        execute(
            requestMono,
            json.serializersModule.serializer(),
            json.serializersModule.serializer()
        )

    open fun <R, T, A : ApiRequest<R, T>> execute(
        requestMono: Mono<A>,
        responseSerializer: KSerializer<R>,
        paramsSerializer: KSerializer<T>
    ): Mono<R> = requestMono
        .flatMap { request: A ->
            request.body()
                .map { this.requestBodyToByteBuffer(paramsSerializer, it) }
                .defaultIfEmpty(Unpooled.EMPTY_BUFFER)
                .onErrorMap(::ApiRequestException)
                .flatMap { body: ByteBuf ->
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
                                ApiResponseException(
                                    body,
                                    null,
                                    statusCode
                                )
                            )
                        }
                    )
                } catch (e: ApiResponseException) {
                    Mono.error(e)
                } catch (error: IOException) {
                    Mono.error(
                        ApiResponseException(
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

    open fun deserializeApiError(body: String): ApiErrorResponse? =
        try {
            doDeserialize<ApiErrorResponse>(
                json.serializersModule.serializer(),
                body
            )
        } catch (other: SerializationException) {
            null
        }

    private fun <T> doDeserialize(serializer: KSerializer<T>, body: String): T =
        json.decodeFromString(serializer, body)

    open fun <T> serializeRequestBody(serializer: KSerializer<T>, body: T): ByteArray {
        val os = ByteArrayOutputStream()
        json.encodeToStream(serializer, body, os)
        return os.toByteArray()
    }

    private fun <T> requestBodyToByteBuffer(serializer: KSerializer<T>, body: T): ByteBuf {
        return try {
            val res: ByteArray = serializeRequestBody<T>(serializer, body)
            Unpooled.wrappedBuffer(res)
        } catch (e: IOException) {
            throw ApiRequestException(e)
        }
    }

    private fun isOkResponse(response: HttpClientResponse) =
        response.status().codeAsText().startsWith("2")
}
