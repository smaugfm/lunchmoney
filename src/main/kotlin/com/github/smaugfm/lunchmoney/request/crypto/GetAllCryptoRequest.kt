package com.github.smaugfm.lunchmoney.request.crypto

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.response.GetAllCryptoResponse

class GetAllCryptoRequest : GetRequest<GetAllCryptoResponse>(
    PathAndQuery.segment("crypto")
)
