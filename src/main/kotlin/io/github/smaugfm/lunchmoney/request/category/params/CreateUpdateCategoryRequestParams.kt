package io.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
internal class CreateUpdateCategoryRequestParams(
    val name: String?,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val description: String?,
    val categoryIds: List<Long>?,
    val groupId: Long?
)
