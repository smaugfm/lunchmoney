package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest

class LunchmoneyGetSingleCategoryRequest(
    id: Long
) : LunchmoneyAbstractGetRequest<LunchmoneyCategorySingle>(
    PathAndQuery.segment("categories").segment(id)
)
