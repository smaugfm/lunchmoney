package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.CreateCategoryGroupRequestParams
import io.github.smaugfm.lunchmoney.response.CreateCategoryResponse

internal class CreateCategoryGroupRequest(
    params: CreateCategoryGroupRequestParams
) : LunchmoneyAbstractPostRequest<
        CreateCategoryResponse,
        CreateCategoryGroupRequestParams
        >(
    PathAndQuery.segment("categories").segment("group"),
    params
)
