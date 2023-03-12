package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse

class GetAllCategoriesRequest : GetRequest<GetAllCategoriesResponse>(
    PathAndQuery.segment("categories")
)
