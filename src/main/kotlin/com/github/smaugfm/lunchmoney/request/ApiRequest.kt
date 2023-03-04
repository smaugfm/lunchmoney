package com.github.smaugfm.lunchmoney.request

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class ApiRequest<TResponse, TBody> {
    abstract val pathAndQuery: PathAndQuery
    abstract fun method(): HttpMethod

    fun pathAndQuery() = pathAndQuery.toString()
    open fun body(): Mono<TBody> = Mono.empty()
}
