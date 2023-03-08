package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.DeleteRequest

class DeleteCategoryRequest(categoryId: Long) : DeleteRequest<Boolean>(
    PathAndQuery.segment("categories").segment(categoryId)
)
