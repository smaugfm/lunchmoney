package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class ForceDeleteCategoryRequest(categoryId: Long) : ApiRequest<Boolean, Void>() {

    override val pathAndQuery =
        PathAndQuery
            .segment("categories")
            .segment(categoryId)
            .segment("force")

    override fun method(): HttpMethod = HttpMethod.DELETE
}
