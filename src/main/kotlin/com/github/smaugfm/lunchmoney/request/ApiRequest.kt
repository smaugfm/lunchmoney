package com.github.smaugfm.lunchmoney.request

import com.github.smaugfm.lunchmoney.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class ApiRequest<R, T> {
    private val pathAndQuery: PathAndQuery? = null

    abstract fun method(): HttpMethod
    fun pathAndQuery(): String {
        return pathAndQuery.toString()
    }

    fun body(): Mono<T> {
        return Mono.empty()
    }
}
