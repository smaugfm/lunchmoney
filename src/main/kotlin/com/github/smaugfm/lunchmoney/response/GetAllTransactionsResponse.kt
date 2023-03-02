package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class GetAllTransactionsResponse(
    val transactions: List<Transaction>
)
