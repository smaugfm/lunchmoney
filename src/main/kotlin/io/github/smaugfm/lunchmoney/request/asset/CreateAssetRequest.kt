package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Asset
import io.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.PostRequest
import reactor.core.publisher.Mono

class CreateAssetRequest(params: Mono<CreateUpdateAssetParams>) :
    PostRequest<Asset, CreateUpdateAssetParams>(
        PathAndQuery.segment("assets"),
        params
    ) {
    constructor(params: CreateUpdateAssetParams) : this(Mono.just(params))
}
