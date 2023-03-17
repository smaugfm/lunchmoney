package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetAllAssetsResponse(
    val assets: List<LunchmoneyAsset>
)
