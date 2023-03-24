package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateCategoryResponse(
    val categoryId: Long
)
