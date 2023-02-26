package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class GetAllCategoriesRequest : ApiRequest<GetAllCategoriesRequest, Unit>() {
    override val pathAndQuery = PathAndQuery.segment("transactions")
    override fun method(): HttpMethod = HttpMethod.GET
}
