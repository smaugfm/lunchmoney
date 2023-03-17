package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryMultiple
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetAllCategoriesResponse(
    val categories: List<LunchmoneyCategoryMultiple>
)
