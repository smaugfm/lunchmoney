package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.request.asset.params.LunchmoneyCreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import reactor.core.publisher.Mono

class LunchmoneyUpdateAssetRequest(assetId: Long, params: Mono<LunchmoneyCreateUpdateAssetParams>) :
    LunchmoneyAbstractPutRequest<LunchmoneyAsset, LunchmoneyCreateUpdateAssetParams>(
        PathAndQuery.segment("assets").segment(assetId),
        params
    ) {
    constructor(assetId: Long, params: LunchmoneyCreateUpdateAssetParams) : this(assetId, Mono.just(params))
}
