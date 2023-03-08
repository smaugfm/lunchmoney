package com.github.smaugfm.lunchmoney.request.asset

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Asset
import com.github.smaugfm.lunchmoney.request.asset.params.CreateAssetParams
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import reactor.core.publisher.Mono

class CreateAssetRequest(params: Mono<CreateAssetParams>) :
    PostRequest<Asset, CreateAssetParams>(params) {
    constructor(params: CreateAssetParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("assets")
}
