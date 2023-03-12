package io.github.smaugfm.lunchmoney.request.crypto

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.response.GetAllCryptoResponse

class GetAllCryptoRequest : GetRequest<GetAllCryptoResponse>(
    PathAndQuery.segment("crypto")
)
