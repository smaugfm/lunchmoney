@file:UseSerializers(
    BigDecimalSerializer::class,
    CryptoSourceSerializer::class,
    InstantSerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyCryptoSource
import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CryptoSourceSerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant

@Serializable
data class LunchmoneyCrypto(
    val id: Long? = null,
    val zaboAccountId: Long? = null,
    val source: LunchmoneyCryptoSource,
    val name: String,
    val displayName: String? = null,
    val balance: BigDecimal,
    val balanceAsOf: Instant? = null,
    val currency: String,
    val status: String,
    val institutionName: String? = null,
    val createdAt: Instant? = null
)
