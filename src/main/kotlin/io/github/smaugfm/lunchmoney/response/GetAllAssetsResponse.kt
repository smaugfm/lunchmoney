package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllAssetsResponse(
    val assets: List<LunchmoneyAsset>
)
