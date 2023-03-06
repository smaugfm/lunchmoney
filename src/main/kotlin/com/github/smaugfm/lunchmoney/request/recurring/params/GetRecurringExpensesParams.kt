@file:UseSerializers(LocalDateSerializer::class)
package com.github.smaugfm.lunchmoney.request.recurring.params

import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class GetRecurringExpensesParams(
    val startDate: LocalDate? = null,
    val debitAsNegative: Boolean? = null,
)
