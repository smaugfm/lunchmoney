package io.github.smaugfm.lunchmoney.request.base

import io.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class BodyRequest<TResponse, TBody : Any>(
    protected val params: Mono<TBody>,
    protected val method: HttpMethod
) : ApiRequest<TResponse, TBody>() {
    constructor(params: TBody, method: HttpMethod) : this(Mono.just(params), method)

    final override fun method(): HttpMethod = method
    final override fun body(): Mono<TBody> = params
}
