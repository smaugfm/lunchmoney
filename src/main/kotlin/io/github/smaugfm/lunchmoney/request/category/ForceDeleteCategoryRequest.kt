package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractDeleteRequest

internal class ForceDeleteCategoryRequest(categoryId: Long) :
    LunchmoneyAbstractDeleteRequest<Boolean>(
        PathAndQuery
            .segment("categories")
            .segment(categoryId)
            .segment("force")
    )
