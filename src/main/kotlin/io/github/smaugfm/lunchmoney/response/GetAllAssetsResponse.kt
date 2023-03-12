package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.Asset
import kotlinx.serialization.Serializable

@Serializable
data class GetAllAssetsResponse(
    val assets: List<Asset>
)
