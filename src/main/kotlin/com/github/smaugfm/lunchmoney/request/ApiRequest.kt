package com.github.smaugfm.lunchmoney.request

import com.github.smaugfm.lunchmoney.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class ApiRequest<R, T> {
    abstract val pathAndQuery: PathAndQuery
    abstract fun method(): HttpMethod

    fun pathAndQuery() = pathAndQuery.toString()
    open fun body(): Mono<T> = Mono.empty()
}
