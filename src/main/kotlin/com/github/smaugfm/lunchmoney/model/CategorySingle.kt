package com.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class CategorySingle(
    val id: Long,
    val name: String,
    val description: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val isGroup: Boolean,
    val groupId: Long? = null,
    val groupCategoryName: String? = null,
    val children: List<CategoryChild>? = null
)
