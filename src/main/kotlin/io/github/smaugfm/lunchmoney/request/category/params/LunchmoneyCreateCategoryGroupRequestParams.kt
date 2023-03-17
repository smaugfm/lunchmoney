package io.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyCreateCategoryGroupRequestParams(
    val name: String,
    val description: String? = null,
    val isIncome: Boolean? = null,
    val excludeFromBudget: Boolean? = null,
    val excludeFromTotals: Boolean? = null,
    val categoryIds: List<Long>? = null,
    val newCategories: List<String>? = null
)
