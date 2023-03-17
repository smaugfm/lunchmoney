package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyRecurringExpense
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetRecurringExpensesResponse(
    val recurringExpenses: List<LunchmoneyRecurringExpense>
)
