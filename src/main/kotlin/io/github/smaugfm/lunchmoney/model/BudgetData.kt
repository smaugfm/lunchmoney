@file:UseSerializers(BigDecimalSerializer::class, CurrencySerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.Currency

@Serializable
data class BudgetData(
    val numTransactions: Long,
    val spendingToBase: Double,
    val budgetToBase: Double? = null,
    val budgetAmount: Double? = null,
    val budgetCurrency: Currency? = null,
    val isAutomated: Boolean? = null
)
