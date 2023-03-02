package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CategoryMultiple(
    val id: Long,
    val name: String,
    val description: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    @Serializable(with = InstantSerializer::class)
    val updatedAt: Instant,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant,
    val isGroup: Boolean,
    val groupId: Long? = null
)
