package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import com.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class CreateCategoryRequest(private val params: Mono<CreateUpdateCategoryRequestParams>) :
    ApiRequest<CreateCategoryResponse, CreateUpdateCategoryRequestParams>() {
    constructor(params: CreateUpdateCategoryRequestParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("categories")
    override fun method(): HttpMethod = HttpMethod.POST
    override fun body() = params
}
