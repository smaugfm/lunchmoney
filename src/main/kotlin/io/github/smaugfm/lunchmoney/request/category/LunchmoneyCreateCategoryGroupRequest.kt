package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyCreateCategoryGroupRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyCreateCategoryResponse
import reactor.core.publisher.Mono

class LunchmoneyCreateCategoryGroupRequest(params: Mono<LunchmoneyCreateCategoryGroupRequestParams>) :
    LunchmoneyAbstractPostRequest<LunchmoneyCreateCategoryResponse, LunchmoneyCreateCategoryGroupRequestParams>(
        PathAndQuery.segment("categories").segment("group"),
        params
    ) {
    constructor(params: LunchmoneyCreateCategoryGroupRequestParams) : this(Mono.just(params))
}
