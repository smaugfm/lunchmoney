package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetAllTransactionsResponse(
    val transactions: List<LunchmoneyTransaction>
)
