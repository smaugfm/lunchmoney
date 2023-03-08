package com.github.smaugfm.lunchmoney.request.crypto

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Crypto
import com.github.smaugfm.lunchmoney.request.base.PutRequest
import com.github.smaugfm.lunchmoney.request.crypto.params.UpdateManualCryptoParams
import reactor.core.publisher.Mono

class UpdateManualCryptoAsset(id: Long, params: Mono<UpdateManualCryptoParams>) :
    PutRequest<Crypto, UpdateManualCryptoParams>(
        PathAndQuery.segment("crypto").segment("manual").segment(id),
        params
    ) {
    constructor(id: Long, params: UpdateManualCryptoParams) : this(id, Mono.just(params))
}
