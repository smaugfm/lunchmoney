package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryMultiple
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllCategoriesResponse(
    val categories: List<LunchmoneyCategoryMultiple>
)
