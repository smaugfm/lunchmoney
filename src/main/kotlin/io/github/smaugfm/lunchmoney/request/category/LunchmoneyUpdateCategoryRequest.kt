package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyCreateUpdateCategoryRequestParams
import reactor.core.publisher.Mono

class LunchmoneyUpdateCategoryRequest(id: Long, params: Mono<LunchmoneyCreateUpdateCategoryRequestParams>) :
    LunchmoneyAbstractPutRequest<Boolean, LunchmoneyCreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories").segment(id),
        params
    ) {
    constructor(id: Long, params: LunchmoneyCreateUpdateCategoryRequestParams) : this(id, Mono.just(params))
}
