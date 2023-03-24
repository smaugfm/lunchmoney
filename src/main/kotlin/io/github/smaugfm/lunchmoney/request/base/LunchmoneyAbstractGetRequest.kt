package io.github.smaugfm.lunchmoney.request.base

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.LunchmoneyAbstractApiRequest
import io.netty.handler.codec.http.HttpMethod

internal abstract class LunchmoneyAbstractGetRequest<TResponse>(
    final override val pathAndQuery: PathAndQuery
) : LunchmoneyAbstractApiRequest<TResponse, Unit>() {

    final override fun method(): HttpMethod = HttpMethod.GET
}
