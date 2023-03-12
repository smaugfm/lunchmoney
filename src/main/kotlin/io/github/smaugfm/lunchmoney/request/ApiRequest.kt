package io.github.smaugfm.lunchmoney.request

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class ApiRequest<TResponse, TBody : Any> {
    abstract val pathAndQuery: PathAndQuery
    abstract fun method(): HttpMethod

    fun pathAndQuery() = pathAndQuery.toString()
    open fun body(): Mono<TBody> = Mono.empty()
}
