package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class DeleteCategoryRequest(categoryId: Long) : ApiRequest<Boolean, Unit>() {

    override val pathAndQuery = PathAndQuery.segment("categories").segment(categoryId)
    override fun method(): HttpMethod = HttpMethod.DELETE
}
