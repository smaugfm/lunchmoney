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
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.serializer
import mu.KotlinLogging
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.netty.ByteBufMono
import reactor.netty.http.client.HttpClient
import reactor.netty.http.client.HttpClientResponse
import java.io.ByteArrayOutputStream
import java.util.function.Function

val log = KotlinLogging.logger { }

internal class RequestExecutor(
    token: String,
    val json: Json,
    private val httpClient: HttpClient,
    private val port: Int = 443,
    private val requestTransformer: Function<Publisher<Any>, Publisher<Any>>?
) {
    private val authorizationHeader = "Bearer $token"

    fun <R, T, A : LunchmoneyAbstractApiRequest<R, T>> execute(
        requestMono: Mono<A>,
        responseSerializer: KSerializer<R>,
        paramsSerializer: KSerializer<T>
    ): Mono<R> = requestMono
        .flatMap { request: A ->
            httpClient
                .port(port)
                .headers { addHeaders(request.method(), it) }
                .request(request.method())
                .uri(request.pathAndQuery())
                .send(requestBodyToByteBuffer(paramsSerializer, request.body()))
                .responseSingle { resp, byteBufMono ->
                    processResponse(resp, byteBufMono, responseSerializer)
                        .doOnNext { log.debug { "Response (${resp.status()}): $it" } }
                }.doOnSubscribe {
                    log.debug { "Performing Lunchmoney API request $request" }
                }.let {
                    @Suppress("UNCHECKED_CAST")
                    if (requestTransformer != null) {
                        it.transformDeferred(
                            requestTransformer as Function<in Publisher<R>, out Publisher<R>>
                        )
                    } else {
                        it
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
    ): Mono<R> =
        byteBufMono
            .asString()
            .flatMap { body: String ->
                deserializeResponseBody(serializer, resp.status().code(), body)
                    .transformDeferred { mapUnknownError(it, body, resp.status().code()) }
            }.transformDeferred { errorOnEmptyResponse(it, resp) }

    private fun <R> errorOnEmptyResponse(mono: Mono<R>, resp: HttpClientResponse): Mono<R> =
        mono.switchIfEmpty(
            if (isOkResponse(resp)) {
                Mono.empty()
            } else {
                Mono.error(LunchmoneyApiResponseException(resp.status().code()))
            }
        )

    private fun <R> mapUnknownError(mono: Mono<R>, body: String, statusCode: Int): Mono<R> =
        mono.onErrorMap({ it !is LunchmoneyApiResponseException }) {
            LunchmoneyApiResponseException(
                body,
                it,
                statusCode
            )
        }

    private fun <R> deserializeResponseBody(
        serializer: KSerializer<R>,
        status: Int,
        body: String
    ): Mono<R> =
        doDeserialize(serializer, body)
            .onErrorResume(SerializationException::class.java) {
                deserializeApiError(body)
                    .flatMap { Mono.error(it.toException(body, status)) }
            }

    private fun deserializeApiError(body: String) =
        doDeserialize<ApiErrorResponse>(json.serializersModule.serializer(), body)

    private fun <T> doDeserialize(serializer: KSerializer<T>, body: String): Mono<T> =
        Mono.fromCallable { json.decodeFromString(serializer, body) }

    private fun <T> serializeRequestBody(serializer: KSerializer<T>, body: T): ByteArray {
        val os = ByteArrayOutputStream()
        json.encodeToStream(serializer, body, os)
        return os.toByteArray()
    }

    private fun <T> requestBodyToByteBuffer(
        serializer: KSerializer<T>,
        body: T?
    ): Mono<ByteBuf> =
        Mono.justOrEmpty(body)
            .mapNotNull { Unpooled.wrappedBuffer(serializeRequestBody(serializer, it!!)) }
            .onErrorMap { LunchmoneyApiRequestException(it) }

    private fun isOkResponse(response: HttpClientResponse) =
        response.status().codeAsText().startsWith("2")
}
