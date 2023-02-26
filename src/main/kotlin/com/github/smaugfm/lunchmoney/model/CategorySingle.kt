package com.github.smaugfm.lunchmoney.model

class CategorySingle(
    val id: Long,
    val name: String,
    val description: String?,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val isGroup: Boolean,
    val groupId: Long?,
    val groupCategoryName: String?,
    val children: List<CategorySingleChild>?
)
