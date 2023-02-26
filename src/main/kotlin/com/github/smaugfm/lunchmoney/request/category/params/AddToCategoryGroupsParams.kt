package com.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
data class AddToCategoryGroupsParams(
    val categoryIds: List<Long>?,
    val newCategories: List<String>?
) {
    class AddToCategoryGroupsParamsBuilder {
        var categoryIds: List<Long>? = null
        var newCategories: List<String>? = null
        fun build(): AddToCategoryGroupsParams =
            AddToCategoryGroupsParams(categoryIds, newCategories)
    }

    companion object {
        fun builder(
            init: (AddToCategoryGroupsParamsBuilder.() -> Unit)?
        ): AddToCategoryGroupsParams =
            AddToCategoryGroupsParamsBuilder()
                .also { builder -> init?.let { builder.it() } }
                .build()
    }
}
