@file:UseSerializers(LocalDateSerializer::class, CurrencySerializer::class)

package io.github.smaugfm.lunchmoney.request.budget.params

import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.util.Currency

@Serializable
internal data class GetBudgetSummaryParams(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currency: Currency?,
)
