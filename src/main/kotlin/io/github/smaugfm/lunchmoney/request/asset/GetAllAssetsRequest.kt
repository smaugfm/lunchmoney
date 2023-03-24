package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.GetAllAssetsResponse

internal class GetAllAssetsRequest :
    LunchmoneyAbstractGetRequest<GetAllAssetsResponse>(
        PathAndQuery.segment("assets")
    )
