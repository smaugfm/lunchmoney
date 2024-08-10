package io.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyBudgetRecurringItemList(
    val list: List<LunchmoneyBudgetRecurringItem>
)
