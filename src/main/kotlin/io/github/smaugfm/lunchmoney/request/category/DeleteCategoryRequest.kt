package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.DeleteRequest

class DeleteCategoryRequest(categoryId: Long) : DeleteRequest<Boolean>(
    PathAndQuery.segment("categories").segment(categoryId)
)
