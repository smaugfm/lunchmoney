package com.github.smaugfm.lunchmoney.request.transaction.params

import kotlinx.serialization.Serializable

@Serializable
data class GetSingleTransactionParams(
    val debitAsNegative: Boolean? = null
)
