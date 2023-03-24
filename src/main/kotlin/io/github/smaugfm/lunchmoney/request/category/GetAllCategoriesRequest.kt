package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse

internal class GetAllCategoriesRequest : LunchmoneyAbstractGetRequest<GetAllCategoriesResponse>(
    PathAndQuery.segment("categories")
)
