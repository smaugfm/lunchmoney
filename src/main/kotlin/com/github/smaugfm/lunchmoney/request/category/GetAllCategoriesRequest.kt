package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse
import io.netty.handler.codec.http.HttpMethod

class GetAllCategoriesRequest : ApiRequest<GetAllCategoriesResponse, Unit>() {
    override val pathAndQuery = PathAndQuery.segment("categories")
    override fun method(): HttpMethod = HttpMethod.GET
}
