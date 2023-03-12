package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.RecurringExpense
import kotlinx.serialization.Serializable

@Serializable
data class GetRecurringExpensesResponse(
    val recurringExpenses: List<RecurringExpense>
)
