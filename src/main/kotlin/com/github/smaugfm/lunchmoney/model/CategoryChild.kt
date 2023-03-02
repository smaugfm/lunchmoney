package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CategoryChild(
    val id: Long,
    val name: String,
    val description: String? = null,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)
