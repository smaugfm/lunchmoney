package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Asset
import io.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.PutRequest
import reactor.core.publisher.Mono

class UpdateAssetRequest(assetId: Long, params: Mono<CreateUpdateAssetParams>) :
    PutRequest<Asset, CreateUpdateAssetParams>(
        PathAndQuery.segment("assets").segment(assetId),
        params
    ) {
    constructor(assetId: Long, params: CreateUpdateAssetParams) : this(assetId, Mono.just(params))
}
