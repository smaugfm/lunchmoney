package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
internal data class InsertTransactionsResponse(
    val ids: List<Long>
)
