package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PostRequest
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import reactor.core.publisher.Mono

class CreateCategoryRequest(params: Mono<CreateUpdateCategoryRequestParams>) :
    PostRequest<CreateCategoryResponse, CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories"),
        params
    ) {
    constructor(params: CreateUpdateCategoryRequestParams) : this(Mono.just(params))
}
