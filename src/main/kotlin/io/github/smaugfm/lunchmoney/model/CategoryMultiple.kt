@file:UseSerializers(InstantSerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Instant

@Serializable
data class CategoryMultiple(
    val id: Long,
    val name: String,
    val description: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val updatedAt: Instant,
    val createdAt: Instant,
    val isGroup: Boolean,
    val groupId: Long? = null
)
