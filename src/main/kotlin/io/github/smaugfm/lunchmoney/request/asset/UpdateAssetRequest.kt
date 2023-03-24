package io.github.smaugfm.lunchmoney.request.asset

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest

internal class UpdateAssetRequest(
    assetId: Long,
    params: CreateUpdateAssetParams
) :
    LunchmoneyAbstractPutRequest<LunchmoneyAsset, CreateUpdateAssetParams>(
        PathAndQuery.segment("assets").segment(assetId),
        params
    )
