package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.CategorySingle
import com.github.smaugfm.lunchmoney.request.base.GetRequest

class GetSingleCategoryRequest(id: Long) : GetRequest<CategorySingle>(
    PathAndQuery.segment("categories").segment(id)
)
