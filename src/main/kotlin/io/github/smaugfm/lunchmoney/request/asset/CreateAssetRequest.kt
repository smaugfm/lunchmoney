package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest

internal class CreateAssetRequest(params: CreateUpdateAssetParams) :
    LunchmoneyAbstractPostRequest<LunchmoneyAsset, CreateUpdateAssetParams>(
        PathAndQuery.segment("assets"),
        params
    )
