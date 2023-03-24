@file:UseSerializers(LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.request.budget.params

import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
internal data class RemoveBudgetParams(
    val startDate: LocalDate,
    val categoryId: Long
)
