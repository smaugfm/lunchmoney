package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyRecurringExpense
import kotlinx.serialization.Serializable

@Serializable
internal data class GetRecurringExpensesResponse(
    val recurringExpenses: List<LunchmoneyRecurringExpense>
)
