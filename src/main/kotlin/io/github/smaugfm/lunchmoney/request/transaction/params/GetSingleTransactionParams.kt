package io.github.smaugfm.lunchmoney.request.transaction.params

import kotlinx.serialization.Serializable

@Serializable
internal data class GetSingleTransactionParams(
    val debitAsNegative: Boolean?
)
