package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.AddToCategoryGroupsParams

internal class AddToCategoryGroupRequest(
    groupId: Long,
    params: AddToCategoryGroupsParams
) : LunchmoneyAbstractPostRequest<LunchmoneyCategorySingle, AddToCategoryGroupsParams>(
    PathAndQuery
        .segment("categories")
        .segment("group")
        .segment(groupId)
        .segment("add"),
    params
)
