@file:UseSerializers(
    BigDecimalSerializer::class,
    CryptoSourceSerializer::class,
    InstantSerializer::class
)

package com.github.smaugfm.lunchmoney.request.crypto.params

import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CryptoSourceSerializer
import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
data class UpdateManualCryptoParams(
    val name: String? = null,
    val displayName: String? = null,
    val balance: BigDecimal? = null,
    val currency: String? = null
)
