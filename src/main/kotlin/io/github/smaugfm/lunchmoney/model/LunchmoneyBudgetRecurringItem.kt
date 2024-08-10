@file:UseSerializers(
    BigDecimalSerializer::class,
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.util.Currency

@Serializable
data class LunchmoneyBudgetRecurringItem(
    val payee: String,
    val amount: BigDecimal,
    val currency: Currency,
    val toBase: Double
)
