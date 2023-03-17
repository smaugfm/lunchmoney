package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyInsertTransactionsResponse(
    val ids: List<Long>
)
