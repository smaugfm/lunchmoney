package com.github.smaugfm.lunchmoney.request.transaction.params

import com.github.smaugfm.lunchmoney.model.InsertOrUpdateTransaction
import kotlinx.serialization.Serializable

@Serializable
data class InsertTransactionRequestParams(
    val transactions: List<InsertOrUpdateTransaction>,
    val applyRules: Boolean? = null,
    val skipDuplicates: Boolean? = null,
    val checkForRecurring: Boolean? = null,
    val debitAsNegative: Boolean? = null,
    val skipBalanceUpdate: Boolean? = null,
)
