package io.github.smaugfm.lunchmoney.request.base

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

internal abstract class LunchmoneyAbstractPutRequest<TResponse, TBody : Any>(
    final override val pathAndQuery: PathAndQuery,
    params: TBody
) : LunchmoneyAbstractBodyRequest<TResponse, TBody>(params, HttpMethod.PUT)
