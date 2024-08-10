package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCategory
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllCategoriesResponse(
    val categories: List<LunchmoneyCategory>
)
