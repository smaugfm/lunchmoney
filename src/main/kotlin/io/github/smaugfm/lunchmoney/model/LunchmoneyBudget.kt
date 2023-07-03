@file:UseSerializers(LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class LunchmoneyBudget(
    val categoryName: String,
    val categoryId: Long? = null,
    val categoryGroupName: String? = null,
    val groupId: Long? = null,
    val isGroup: Boolean,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val data: Map<LocalDate, LunchmoneyBudgetData>? = null,
    val config: LunchmoneyBudgetConfig? = null,
    val order: Int? = null
)
