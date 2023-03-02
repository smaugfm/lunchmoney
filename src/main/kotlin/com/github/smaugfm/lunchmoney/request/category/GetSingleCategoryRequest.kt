package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.CategorySingle
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class GetSingleCategoryRequest(id: Long) : ApiRequest<CategorySingle, Unit>() {
    override val pathAndQuery = PathAndQuery.segment("categories").segment(id)
    override fun method(): HttpMethod = HttpMethod.GET
}
