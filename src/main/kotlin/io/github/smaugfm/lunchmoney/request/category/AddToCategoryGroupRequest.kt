package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.CategorySingle
import io.github.smaugfm.lunchmoney.request.base.PostRequest
import io.github.smaugfm.lunchmoney.request.category.params.AddToCategoryGroupsParams
import reactor.core.publisher.Mono

class AddToCategoryGroupRequest(
    groupId: Long,
    params: Mono<AddToCategoryGroupsParams>
) : PostRequest<CategorySingle, AddToCategoryGroupsParams>(
    PathAndQuery
        .segment("categories")
        .segment("group")
        .segment(groupId)
        .segment("add"),
    params
) {
    constructor(groupId: Long, params: AddToCategoryGroupsParams) : this(groupId, Mono.just(params))
}
