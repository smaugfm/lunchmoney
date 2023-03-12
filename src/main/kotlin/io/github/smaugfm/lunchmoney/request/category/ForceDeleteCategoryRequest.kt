package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.DeleteRequest

class ForceDeleteCategoryRequest(categoryId: Long) : DeleteRequest<Boolean>(
    PathAndQuery
        .segment("categories")
        .segment(categoryId)
        .segment("force")
)
