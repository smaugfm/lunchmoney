@file:UseSerializers(LocalDateSerializer::class)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class Budget(
    val categoryName: String,
    val categoryId: Long,
    val categoryGroupName: String? = null,
    val groupId: Long? = null,
    val isGroup: Boolean,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val data: Map<LocalDate, BudgetData>,
    val config: BudgetConfig? = null,
    val order: Int? = null
)
