package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
internal data class DeleteTransactionGroupResponse(
    val transactions: List<Long>
)
