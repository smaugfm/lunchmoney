package com.github.smaugfm.lunchmoney.request.base

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

abstract class GetRequest<TResponse>(
    final override val pathAndQuery: PathAndQuery
) : ApiRequest<TResponse, Unit>() {

    final override fun method(): HttpMethod = HttpMethod.GET
}
