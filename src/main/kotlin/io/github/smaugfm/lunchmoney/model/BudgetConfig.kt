@file:UseSerializers(BigDecimalSerializer::class, CurrencySerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.Currency

@Serializable
data class BudgetConfig(
    val configId: Long,
    val cadence: String,
    val amount: Double,
    val currency: Currency,
    val toBase: Double,
    val autoSuggest: String
)
