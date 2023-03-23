package io.github.smaugfm.lunchmoney.api

import io.github.smaugfm.lunchmoney.request.LunchmoneyAbstractApiRequest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.serializer
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

@OptIn(ExperimentalSerializationApi::class)
open class LunchmoneyApi internal constructor(
    token: String,
    baseUrl: String,
    port: Int,
    jsonBuilderActions: List<JsonBuilder.() -> Unit> = listOf(DEFAULT_JSON_BUILDER),
    reactorNettyConnectionProvider: ConnectionProvider? = null
) {
    constructor(
        token: String,
        jsonBuilderAction: JsonBuilder.() -> Unit = {},
        reactorNettyConnectionProvider: ConnectionProvider? = null
    ) : this(
        token,
        "https://dev.lunchmoney.app/v1",
        DEFAULT_HTTP_PORT,
        listOf(jsonBuilderAction, DEFAULT_JSON_BUILDER),
        reactorNettyConnectionProvider
    )

    val requestExecutor = RequestExecutor(
        token,
        Json(builderAction = {
            jsonBuilderActions.forEach { this.it() }
        }),
        port,
        if (reactorNettyConnectionProvider != null) {
            HttpClient.create(reactorNettyConnectionProvider).baseUrl(baseUrl)
        } else {
            HttpClient.create().baseUrl(baseUrl)
        }
    )

    inline fun <reified R, reified T, A : LunchmoneyAbstractApiRequest<R, T>> execute(request: A): Mono<R> =
        execute<R, T, A>(Mono.just(request))

    inline fun <reified R, reified T, A : LunchmoneyAbstractApiRequest<R, T>> execute(requestMono: Mono<A>): Mono<R> =
        requestExecutor.execute(
            requestMono,
            requestExecutor.json.serializersModule.serializer(),
            requestExecutor.json.serializersModule.serializer()
        )

    companion object {
        private const val DEFAULT_HTTP_PORT = 443
        private val DEFAULT_JSON_BUILDER: JsonBuilder.() -> Unit = {
            namingStrategy = JsonNamingStrategy.SnakeCase
            ignoreUnknownKeys = true
        }
    }
}
