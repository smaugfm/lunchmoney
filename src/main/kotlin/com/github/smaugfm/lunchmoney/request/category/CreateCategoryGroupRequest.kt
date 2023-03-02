package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateCategoryGroupRequestParams
import com.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class CreateCategoryGroupRequest(private val params: Mono<CreateCategoryGroupRequestParams>) :
    ApiRequest<CreateCategoryResponse, CreateCategoryGroupRequestParams>() {
    constructor(params: CreateCategoryGroupRequestParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("categories").segment("group")
    override fun method(): HttpMethod = HttpMethod.POST
    override fun body() = params
}
