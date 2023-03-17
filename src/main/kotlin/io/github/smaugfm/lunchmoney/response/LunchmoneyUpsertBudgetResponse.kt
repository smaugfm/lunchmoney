@file:UseSerializers(LocalDateSerializer::class, CurrencySerializer::class)

package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.util.Currency

@Serializable
data class LunchmoneyUpsertBudgetResponse(
    val categoryGroup: UpsertBudgetCategoryGroupResponse? = null
) {
    @Serializable
    data class UpsertBudgetCategoryGroupResponse(
        val categoryId: Long,
        val amount: Double,
        val currency: Currency,
        val startDate: LocalDate
    )
}
