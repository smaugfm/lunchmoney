package io.github.smaugfm.lunchmoney.request.category.params

import kotlinx.serialization.Serializable

@Serializable
internal data class AddToCategoryGroupsParams(
    val categoryIds: List<Long>?,
    val newCategories: List<String>?,
)
