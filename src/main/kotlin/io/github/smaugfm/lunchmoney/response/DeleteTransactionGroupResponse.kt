package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class DeleteTransactionGroupResponse(
    val transactions: List<Long>
)
