package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
internal data class UpsertBudgetResponse(
    val categoryGroup: UpsertBudgetCategoryGroupResponse? = null
)
