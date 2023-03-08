package com.github.smaugfm.lunchmoney.request.asset

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.response.GetAllAssetsResponse

class GetAllAssetsRequest : GetRequest<GetAllAssetsResponse>(
    PathAndQuery.segment("assets")
)
