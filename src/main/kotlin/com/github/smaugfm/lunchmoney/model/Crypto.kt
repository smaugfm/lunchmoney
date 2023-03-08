@file:UseSerializers(
    BigDecimalSerializer::class,
    CryptoSourceSerializer::class,
    InstantSerializer::class
)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.model.enumeration.CryptoSource
import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CryptoSourceSerializer
import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant

@Serializable
data class Crypto(
    val id: Long? = null,
    val zaboAccountId: Long? = null,
    val source: CryptoSource,
    val name: String,
    val displayName: String? = null,
    val balance: BigDecimal,
    val balanceAsOf: Instant? = null,
    val currency: String,
    val status: String,
    val institutionName: String? = null,
    val createdAt: Instant? = null,
)
