package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams

internal class UpdateCategoryRequest(
    id: Long,
    params: CreateUpdateCategoryRequestParams
) :
    LunchmoneyAbstractPutRequest<Boolean, CreateUpdateCategoryRequestParams>(
        PathAndQuery.segment("categories").segment(id),
        params
    )
