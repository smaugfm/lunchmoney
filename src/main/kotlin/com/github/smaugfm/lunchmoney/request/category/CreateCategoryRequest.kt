package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import com.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import reactor.core.publisher.Mono

class CreateCategoryRequest(params: Mono<CreateUpdateCategoryRequestParams>) :
    PostRequest<CreateCategoryResponse, CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories"),
        params
    ) {
    constructor(params: CreateUpdateCategoryRequestParams) : this(Mono.just(params))
}
