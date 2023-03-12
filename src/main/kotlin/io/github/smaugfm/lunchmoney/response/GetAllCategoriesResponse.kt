package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.CategoryMultiple
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCategoriesResponse(
    val categories: List<CategoryMultiple>
)
