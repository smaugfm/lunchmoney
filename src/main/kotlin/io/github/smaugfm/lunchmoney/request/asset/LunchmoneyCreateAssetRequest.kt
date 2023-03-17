package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.request.asset.params.LunchmoneyCreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import reactor.core.publisher.Mono

class LunchmoneyCreateAssetRequest(params: Mono<LunchmoneyCreateUpdateAssetParams>) :
    LunchmoneyAbstractPostRequest<LunchmoneyAsset, LunchmoneyCreateUpdateAssetParams>(
        PathAndQuery.segment("assets"),
        params
    ) {
    constructor(params: LunchmoneyCreateUpdateAssetParams) : this(Mono.just(params))
}
