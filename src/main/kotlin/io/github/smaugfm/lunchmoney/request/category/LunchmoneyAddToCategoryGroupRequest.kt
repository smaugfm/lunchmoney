package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyAddToCategoryGroupsParams
import reactor.core.publisher.Mono

class LunchmoneyAddToCategoryGroupRequest(
    groupId: Long,
    params: Mono<LunchmoneyAddToCategoryGroupsParams>
) : LunchmoneyAbstractPostRequest<LunchmoneyCategorySingle, LunchmoneyAddToCategoryGroupsParams>(
    PathAndQuery
        .segment("categories")
        .segment("group")
        .segment(groupId)
        .segment("add"),
    params
) {
    constructor(groupId: Long, params: LunchmoneyAddToCategoryGroupsParams) : this(groupId, Mono.just(params))
}
