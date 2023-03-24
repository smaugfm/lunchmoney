package io.github.smaugfm.lunchmoney.request

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.netty.handler.codec.http.HttpMethod

internal abstract class LunchmoneyAbstractApiRequest<TResponse, TBody : Any> {
    abstract val pathAndQuery: PathAndQuery
    abstract fun method(): HttpMethod

    fun pathAndQuery() = pathAndQuery.toString()
    open fun body(): TBody? = null
}
