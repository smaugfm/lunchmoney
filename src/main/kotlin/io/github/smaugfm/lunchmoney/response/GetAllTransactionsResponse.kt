package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllTransactionsResponse(
    val transactions: List<LunchmoneyTransaction>,
    val hasMore: Boolean
)
