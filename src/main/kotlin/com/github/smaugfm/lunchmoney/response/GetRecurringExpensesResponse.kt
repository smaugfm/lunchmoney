package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.RecurringExpense
import kotlinx.serialization.Serializable

@Serializable
data class GetRecurringExpensesResponse(
    val recurringExpenses: List<RecurringExpense>
)
