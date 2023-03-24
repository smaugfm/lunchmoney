package io.github.smaugfm.lunchmoney.request.crypto

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.crypto.params.UpdateManualCryptoParams

internal class UpdateManualCryptoAsset(id: Long, params: UpdateManualCryptoParams) :
    LunchmoneyAbstractPutRequest<LunchmoneyCrypto, UpdateManualCryptoParams>(
        PathAndQuery.segment("crypto").segment("manual").segment(id),
        params
    )
