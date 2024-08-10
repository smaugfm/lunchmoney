@file:UseSerializers(
    LocalDateSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class,
    InstantSerializer::class,
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

@Serializable
data class LunchmoneyTransactionChild(
    val id: Long,
    val date: LocalDate,
    val payee: String,
    val amount: BigDecimal,
    val currency: Currency,
    val formattedDate: LocalDate,
    val notes: String? = null,
    val assetId: Long? = null,
    val plaidAccountId: Long? = null,
    val toBase: Double
)
