package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategory
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest

internal class GetSingleCategoryRequest(
    id: Long
) : LunchmoneyAbstractGetRequest<LunchmoneyCategory>(
    PathAndQuery.segment("categories").segment(id)
)
