package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PutRequest
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import reactor.core.publisher.Mono

class UpdateCategoryRequest(id: Long, params: Mono<CreateUpdateCategoryRequestParams>) :
    PutRequest<Boolean, CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories").segment(id),
        params
    ) {
    constructor(id: Long, params: CreateUpdateCategoryRequestParams) : this(id, Mono.just(params))
}
