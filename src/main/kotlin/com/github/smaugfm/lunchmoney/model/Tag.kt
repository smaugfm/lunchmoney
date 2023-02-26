package com.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @Required
    val id: Long,
    @Required
    val name: String,
    val description: String?
)
