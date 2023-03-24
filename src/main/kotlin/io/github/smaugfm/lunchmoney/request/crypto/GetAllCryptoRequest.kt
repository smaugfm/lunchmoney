package io.github.smaugfm.lunchmoney.request.crypto

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.GetAllCryptoResponse

internal class GetAllCryptoRequest :
    LunchmoneyAbstractGetRequest<GetAllCryptoResponse>(
        PathAndQuery.segment("crypto")
    )
