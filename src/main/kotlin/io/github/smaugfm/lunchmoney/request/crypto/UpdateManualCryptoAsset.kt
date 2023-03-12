package io.github.smaugfm.lunchmoney.request.crypto

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Crypto
import io.github.smaugfm.lunchmoney.request.base.PutRequest
import io.github.smaugfm.lunchmoney.request.crypto.params.UpdateManualCryptoParams
import reactor.core.publisher.Mono

class UpdateManualCryptoAsset(id: Long, params: Mono<UpdateManualCryptoParams>) :
    PutRequest<Crypto, UpdateManualCryptoParams>(
        PathAndQuery.segment("crypto").segment("manual").segment(id),
        params
    ) {
    constructor(id: Long, params: UpdateManualCryptoParams) : this(id, Mono.just(params))
}
