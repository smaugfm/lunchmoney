package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PutRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import reactor.core.publisher.Mono

class UpdateCategoryRequest(id: Long, params: Mono<CreateUpdateCategoryRequestParams>) :
    PutRequest<Boolean, CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories").segment(id),
        params
    ) {
    constructor(id: Long, params: CreateUpdateCategoryRequestParams) : this(id, Mono.just(params))
}
