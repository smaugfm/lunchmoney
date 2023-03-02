package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.CategoryMultiple
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCategoriesResponse(
    val categories: List<CategoryMultiple>
)

