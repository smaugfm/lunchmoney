package com.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
data class AddToCategoryGroupsParams(
    val categoryIds: List<Long>? = null,
    val newCategories: List<String>? = null
)
