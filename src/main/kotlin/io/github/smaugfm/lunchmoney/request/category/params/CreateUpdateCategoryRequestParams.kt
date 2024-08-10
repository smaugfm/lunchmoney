package io.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
internal class CreateUpdateCategoryRequestParams(
    val name: String?,
    val description: String? = null,
    val isIncome: Boolean? = null,
    val excludeFromBudget: Boolean? = null,
    val excludeFromTotals: Boolean? = null,
    val archived: Boolean? = null,
    val groupId: Long? = null
)
