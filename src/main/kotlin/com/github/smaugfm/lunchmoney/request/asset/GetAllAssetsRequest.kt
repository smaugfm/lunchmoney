package com.github.smaugfm.lunchmoney.request.asset

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.response.GetAllAssetsResponse
import io.netty.handler.codec.http.HttpMethod

class GetAllAssetsRequest : ApiRequest<GetAllAssetsResponse, Unit>() {
    override val pathAndQuery = PathAndQuery.segment("assets")

    override fun method(): HttpMethod = HttpMethod.GET
}
