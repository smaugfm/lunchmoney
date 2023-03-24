package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllCryptoResponse(
    val crypto: List<LunchmoneyCrypto>
)
