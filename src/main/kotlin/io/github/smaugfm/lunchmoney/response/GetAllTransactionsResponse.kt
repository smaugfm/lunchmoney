package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class GetAllTransactionsResponse(
    val transactions: List<Transaction>
)
