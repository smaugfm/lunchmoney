@file:UseSerializers(LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.request.recurring.params

import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class LunchmoneyGetRecurringExpensesParams(
    val startDate: LocalDate? = null,
    val debitAsNegative: Boolean? = null
)
