package com.github.smaugfm.lunchmoney.request.base

import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class PostRequest<TResponse, TBody : Any>(
    params: Mono<TBody>,
) : BodyRequest<TResponse, TBody>(params, HttpMethod.POST) {
    constructor(params: TBody) : this(Mono.just(params))
}
