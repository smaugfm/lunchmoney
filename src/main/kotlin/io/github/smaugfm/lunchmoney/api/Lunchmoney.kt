package io.github.smaugfm.lunchmoney.api

import io.github.smaugfm.lunchmoney.request.ApiRequest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.serializer
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

@OptIn(ExperimentalSerializationApi::class)
open class Lunchmoney(
    token: String,
    baseUrl: String = "https://dev.lunchmoney.app/v1",
    port: Int = 443,
    jsonBuilderAction: JsonBuilder.() -> Unit = {
        namingStrategy = JsonNamingStrategy.SnakeCase
    },
    reactorNettyConnectionProvider: ConnectionProvider? = null
) {
    val requestExecutor = RequestExecutor(
        token,
        Json(builderAction = jsonBuilderAction),
        port,
        if (reactorNettyConnectionProvider != null) {
            HttpClient.create(reactorNettyConnectionProvider).baseUrl(baseUrl)
        } else {
            HttpClient.create().baseUrl(baseUrl)
        }
    )

    inline fun <reified R, reified T, A : ApiRequest<R, T>> execute(request: A): Mono<R> =
        execute<R, T, A>(Mono.just(request))

    inline fun <reified R, reified T, A : ApiRequest<R, T>> execute(requestMono: Mono<A>): Mono<R> =
        requestExecutor.execute(
            requestMono,
            requestExecutor.json.serializersModule.serializer(),
            requestExecutor.json.serializersModule.serializer()
        )
}
