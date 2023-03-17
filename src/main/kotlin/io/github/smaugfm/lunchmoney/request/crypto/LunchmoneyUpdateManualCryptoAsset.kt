package io.github.smaugfm.lunchmoney.request.crypto

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.crypto.params.LunchmoneyUpdateManualCryptoParams
import reactor.core.publisher.Mono

class LunchmoneyUpdateManualCryptoAsset(id: Long, params: Mono<LunchmoneyUpdateManualCryptoParams>) :
    LunchmoneyAbstractPutRequest<LunchmoneyCrypto, LunchmoneyUpdateManualCryptoParams>(
        PathAndQuery.segment("crypto").segment("manual").segment(id),
        params
    ) {
    constructor(id: Long, params: LunchmoneyUpdateManualCryptoParams) : this(id, Mono.just(params))
}
