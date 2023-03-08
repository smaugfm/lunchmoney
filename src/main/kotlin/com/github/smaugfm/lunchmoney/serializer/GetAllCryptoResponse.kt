package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.Crypto
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCryptoResponse(
    val crypto: List<Crypto>
)
