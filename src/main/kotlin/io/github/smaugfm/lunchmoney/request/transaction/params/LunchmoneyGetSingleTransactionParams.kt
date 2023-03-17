package io.github.smaugfm.lunchmoney.request.transaction.params

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetSingleTransactionParams(
    val debitAsNegative: Boolean? = null
)
