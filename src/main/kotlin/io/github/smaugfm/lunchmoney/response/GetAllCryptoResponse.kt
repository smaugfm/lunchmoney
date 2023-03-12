package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.Crypto
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCryptoResponse(
    val crypto: List<Crypto>
)
