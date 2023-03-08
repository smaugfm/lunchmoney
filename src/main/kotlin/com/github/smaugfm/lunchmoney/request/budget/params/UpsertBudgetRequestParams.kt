@file:UseSerializers(LocalDateSerializer::class, CurrencySerializer::class)

package com.github.smaugfm.lunchmoney.request.budget.params

import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.util.Currency

@Serializable
data class UpsertBudgetRequestParams(
    val startDate: LocalDate,
    val categoryId: Long,
    val amount: Double,
    val currency: Currency? = null
)
