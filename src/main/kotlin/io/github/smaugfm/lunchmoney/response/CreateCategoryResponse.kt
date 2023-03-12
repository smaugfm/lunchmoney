package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class CreateCategoryResponse(
    val categoryId: Long
)
