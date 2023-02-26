package com.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
class CreateUpdateCategoryRequestParams(
    val name: String,
    val description: String?,
    val isIncome: Boolean?,
    val excludeFromBudget: Boolean?,
    val excludeFromTotals: Boolean?,
    val categoryIds: List<Long>?,
    val groupId: Long?
) {
    class CreateUpdateCategoryRequestParamsBuilder(val name: String) {
        var description: String? = null
        var isIncome: Boolean? = null
        var excludeFromBudget: Boolean? = null
        var excludeFromTotals: Boolean? = null
        var categoryIds: List<Long>? = null
        var groupId: Long? = null

        fun build(): CreateUpdateCategoryRequestParams =
            CreateUpdateCategoryRequestParams(
                name,
                description,
                isIncome,
                excludeFromBudget,
                excludeFromTotals,
                categoryIds,
                groupId
            )
    }

    companion object {
        fun builder(
            name: String,
            init: (CreateUpdateCategoryRequestParamsBuilder.() -> Unit)?
        ): CreateUpdateCategoryRequestParams =
            CreateUpdateCategoryRequestParamsBuilder(name)
                .also { builder -> init?.let { builder.it() } }
                .build()
    }
}
