package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.response.GetAllAssetsResponse

class GetAllAssetsRequest : GetRequest<GetAllAssetsResponse>(
    PathAndQuery.segment("assets")
)
