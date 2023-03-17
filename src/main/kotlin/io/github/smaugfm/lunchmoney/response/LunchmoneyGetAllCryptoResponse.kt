package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetAllCryptoResponse(
    val crypto: List<LunchmoneyCrypto>
)
