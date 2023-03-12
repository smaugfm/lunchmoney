package io.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Long,
    val name: String,
    val description: String? = null
)
