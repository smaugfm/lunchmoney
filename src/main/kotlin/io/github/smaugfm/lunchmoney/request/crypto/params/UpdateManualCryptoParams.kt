@file:UseSerializers(
    BigDecimalSerializer::class,
    CryptoSourceSerializer::class,
    InstantSerializer::class
)

package io.github.smaugfm.lunchmoney.request.crypto.params

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CryptoSourceSerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal

@Serializable
internal data class UpdateManualCryptoParams(
    val name: String?,
    val displayName: String?,
    val institutionName: String?,
    val balance: BigDecimal?,
    val currency: String?,
)
