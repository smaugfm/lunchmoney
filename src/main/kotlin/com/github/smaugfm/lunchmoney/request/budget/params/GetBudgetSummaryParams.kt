@file:UseSerializers(LocalDateSerializer::class, CurrencySerializer::class)

package com.github.smaugfm.lunchmoney.request.budget.params

import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.util.Currency

@Serializable
data class GetBudgetSummaryParams(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currency: Currency? = null
)
