package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.response.CreateCategoryResponse

internal class CreateCategoryRequest(
    params: CreateUpdateCategoryRequestParams
) :
    LunchmoneyAbstractPostRequest<CreateCategoryResponse,
        CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories"),
        params
    )
