@file:UseSerializers(LocalDateSerializer::class)

package com.github.smaugfm.lunchmoney.request.budget.params

import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class RemoveBudgetParams(
    val startDate: LocalDate,
    val categoryId: Long
)
