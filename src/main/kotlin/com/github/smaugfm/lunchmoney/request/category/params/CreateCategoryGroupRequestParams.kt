package com.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateCategoryGroupRequestParams(
    val name: String,
    val description: String?,
    val isIncome: Boolean?,
    val excludeFromBudget: Boolean?,
    val excludeFromTotals: Boolean?,
    val categoryIds: List<Long>?,
    val newCategories: List<String>?
) {
    class CreateCategoryGroupRequestParamsBuilder(val name: String) {
        var description: String? = null
        var isIncome: Boolean? = null
        var excludeFromBudget: Boolean? = null
        var excludeFromTotals: Boolean? = null
        var categoryIds: List<Long>? = null
        var newCategories: List<String>? = null

        fun build(): CreateCategoryGroupRequestParams =
            CreateCategoryGroupRequestParams(
                name,
                description,
                isIncome,
                excludeFromBudget,
                excludeFromTotals,
                categoryIds,
                newCategories
            )
    }

    companion object {

        fun builder(
            name: String,
            init: (CreateCategoryGroupRequestParamsBuilder.() -> Unit)?
        ): CreateCategoryGroupRequestParams =
            CreateCategoryGroupRequestParamsBuilder(name)
                .also { builder -> init?.let { builder.it() } }
                .build()
    }
}
