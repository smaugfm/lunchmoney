package com.github.smaugfm.lunchmoney.request.asset

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Asset
import com.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import reactor.core.publisher.Mono

class CreateAssetRequest(params: Mono<CreateUpdateAssetParams>) :
    PostRequest<Asset, CreateUpdateAssetParams>(
        PathAndQuery.segment("assets"),
        params
    ) {
    constructor(params: CreateUpdateAssetParams) : this(Mono.just(params))
}
