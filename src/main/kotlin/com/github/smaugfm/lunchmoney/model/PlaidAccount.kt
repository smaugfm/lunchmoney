@file:UseSerializers(
    LocalDateSerializer::class,
    InstantSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class
)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

@Serializable
data class PlaidAccount(
    val id: Long,
    val dateLinked: Instant,
    val name: String,
    val type: String,
    val subtype: String,
    val mask: String,
    val institutionName: String,
    val status: String,
    val lastImport: Instant,
    val balance: BigDecimal,
    val currency: Currency,
    val balanceLastUpdate: Instant,
    val limit: Long? = null
)
