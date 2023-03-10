package io.github.smaugfm.lunchmoney.request.base

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

abstract class PutRequest<TResponse, TBody : Any>(
    final override val pathAndQuery: PathAndQuery,
    params: Mono<TBody>
) : BodyRequest<TResponse, TBody>(params, HttpMethod.PUT)
