@file:UseSerializers(BigDecimalSerializer::class, CurrencySerializer::class)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
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
    val isAutomated: Boolean? = null,
)
