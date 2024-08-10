@file:UseSerializers(InstantSerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Instant

@Serializable
data class LunchmoneyCategory(
    val id: Long,
    val name: String,
    val description: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val archived: Boolean? = null,
    val archivedOn: Instant? = null,
    val updatedAt: Instant? = null,
    val createdAt: Instant? = null,
    val isGroup: Boolean? = null,
    val groupCategoryName: String? = null,
    val groupId: Long? = null,
    val order: Long? = null,
    val children: List<LunchmoneyCategoryChild>? = null
)
