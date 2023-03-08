package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse

class GetAllCategoriesRequest : GetRequest<GetAllCategoriesResponse>(
    PathAndQuery.segment("categories")
)
