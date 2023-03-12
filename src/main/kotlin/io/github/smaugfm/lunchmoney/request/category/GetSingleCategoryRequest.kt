package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.CategorySingle
import io.github.smaugfm.lunchmoney.request.base.GetRequest

class GetSingleCategoryRequest(id: Long) : GetRequest<CategorySingle>(
    PathAndQuery.segment("categories").segment(id)
)
