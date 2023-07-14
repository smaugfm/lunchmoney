package io.github.smaugfm.lunchmoney.api

import io.github.smaugfm.lunchmoney.request.LunchmoneyAbstractApiRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.serializer
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.util.function.Function

open class LunchmoneyApiInternal internal constructor(
    token: String,
    baseUrl: String,
    port: Int,
    jsonBuilderCustomizer: List<JsonBuilder.() -> Unit>,
    reactorNettyConnectionProvider: ConnectionProvider?,
    requestTransformer: Function<Publisher<Any>, Publisher<Any>>?
) {
    internal val requestExecutor = RequestExecutor(
        token,
        Json(builderAction = {
            jsonBuilderCustomizer.forEach { this.it() }
        }),
        if (reactorNettyConnectionProvider != null) {
            HttpClient.create(reactorNettyConnectionProvider).baseUrl(baseUrl)
        } else {
            HttpClient.create().baseUrl(baseUrl)
        },
        port,
        requestTransformer
    )

    internal inline fun <reified R, reified T, A : LunchmoneyAbstractApiRequest<R, T>> execute(
        request: A
    ): Mono<R> =
        execute<R, T, A>(Mono.just(request))

    internal inline fun <reified R, reified T, A : LunchmoneyAbstractApiRequest<R, T>> execute(
        requestMono: Mono<A>
    ): Mono<R> =
        requestExecutor.execute(
            requestMono,
            requestExecutor.json.serializersModule.serializer(),
            requestExecutor.json.serializersModule.serializer()
        )

    companion object {
        internal const val DEFAULT_HTTP_PORT = 443
        internal val DEFAULT_JSON_BUILDER: JsonBuilder.() -> Unit = {
            namingStrategy = JsonNamingStrategy.SnakeCase
            ignoreUnknownKeys = true
        }
    }
}
