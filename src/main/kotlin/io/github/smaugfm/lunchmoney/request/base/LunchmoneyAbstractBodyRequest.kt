package io.github.smaugfm.lunchmoney.request.base

import io.github.smaugfm.lunchmoney.request.LunchmoneyAbstractApiRequest
import io.netty.handler.codec.http.HttpMethod

internal abstract class LunchmoneyAbstractBodyRequest<TResponse, TBody : Any>(
    protected val params: TBody,
    private val method: HttpMethod
) : LunchmoneyAbstractApiRequest<TResponse, TBody>() {

    final override fun method(): HttpMethod = method
    final override fun body() = params
}
