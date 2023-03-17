package io.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyAddToCategoryGroupsParams(
    val categoryIds: List<Long>? = null,
    val newCategories: List<String>? = null
)
