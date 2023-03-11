package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.Crypto
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCryptoResponse(
    val crypto: List<Crypto>
)
