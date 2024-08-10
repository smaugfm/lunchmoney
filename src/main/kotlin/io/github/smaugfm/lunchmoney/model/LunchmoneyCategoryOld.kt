package io.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyCategoryOld(
    val id: Long,
    val name: String,
    val description: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val isGroup: Boolean,
    val groupId: Long? = null,
    val groupCategoryName: String? = null,
    val children: List<LunchmoneyCategoryChild>? = null
)
