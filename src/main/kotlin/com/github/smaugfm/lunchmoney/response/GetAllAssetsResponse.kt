package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.Asset
import kotlinx.serialization.Serializable

@Serializable
data class GetAllAssetsResponse(
    val assets: List<Asset>
)
