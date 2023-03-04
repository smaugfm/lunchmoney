package com.github.smaugfm.lunchmoney.request.recurring.params

import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class GetRecurringExpensesParams(
    @Serializable(with = LocalDateSerializer::class)
    val startDate: LocalDate? = null,
    val debitAsNegative: Boolean? = null,
)
