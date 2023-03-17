package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetAllAssetsResponse

class LunchmoneyGetAllAssetsRequest : LunchmoneyAbstractGetRequest<LunchmoneyGetAllAssetsResponse>(
    PathAndQuery.segment("assets")
)
