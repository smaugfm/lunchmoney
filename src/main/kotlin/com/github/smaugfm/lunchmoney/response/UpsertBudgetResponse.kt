@file:UseSerializers(LocalDateSerializer::class, CurrencySerializer::class)

package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.util.Currency

@Serializable
data class UpsertBudgetResponse(
    val categoryGroup: UpsertBudgetCategoryGroupResponse? = null
) {
    @Serializable
    data class UpsertBudgetCategoryGroupResponse(
        val categoryId: Long,
        val amount: Double,
        val currency: Currency,
        val startDate: LocalDate,
    )
}
