package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyCreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyCreateCategoryResponse
import reactor.core.publisher.Mono

class LunchmoneyCreateCategoryRequest(params: Mono<LunchmoneyCreateUpdateCategoryRequestParams>) :
    LunchmoneyAbstractPostRequest<LunchmoneyCreateCategoryResponse, LunchmoneyCreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories"),
        params
    ) {
    constructor(params: LunchmoneyCreateUpdateCategoryRequestParams) : this(Mono.just(params))
}
