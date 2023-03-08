@file:UseSerializers(
    AssetTypeSerializer::class,
    BigDecimalSerializer::class,
    InstantSerializer::class,
    LocalDateSerializer::class,
    CurrencySerializer::class
)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.model.enumeration.AssetType
import com.github.smaugfm.lunchmoney.serializer.AssetTypeSerializer
import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

@Serializable
data class Asset(
    val id: Long,
    val typeName: AssetType,
    val subtypeName: String,
    val name: String,
    val displayName: String? = null,
    val balance: BigDecimal,
    val balanceAsOf: Instant,
    val closedOn: LocalDate? = null,
    val currency: Currency,
    val institutionName: String,
    val excludeTransactions: Boolean,
    val createdAt: Instant
)
