package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyCreateCategoryResponse(
    val categoryId: Long
)
