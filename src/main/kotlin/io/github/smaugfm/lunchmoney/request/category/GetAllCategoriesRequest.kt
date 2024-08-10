package io.github.smaugfm.lunchmoney.request.category

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.category.params.GetAllCategoriesParams
import io.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse

internal class GetAllCategoriesRequest(val params: GetAllCategoriesParams? = null) :
    LunchmoneyAbstractGetRequest<GetAllCategoriesResponse>(
        PathAndQuery.segment("categories").query(params)
    )
