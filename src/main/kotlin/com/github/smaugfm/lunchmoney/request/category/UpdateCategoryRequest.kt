package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class UpdateCategoryRequest(id: Long, private val params: Mono<CreateUpdateCategoryRequestParams>) :
    ApiRequest<Boolean, CreateUpdateCategoryRequestParams>() {
    constructor(id: Long, params: CreateUpdateCategoryRequestParams) : this(id, Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("categories").segment(id)
    override fun method(): HttpMethod = HttpMethod.PUT
}
