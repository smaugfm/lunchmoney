package com.github.smaugfm.lunchmoney.request.transaction.params

import com.github.smaugfm.lunchmoney.model.InsertOrUpdateTransaction
import com.github.smaugfm.lunchmoney.model.Split
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTransactionParams(
    val transaction: InsertOrUpdateTransaction,
    val split: Split? = null,
    val debitAsNegative: Boolean? = null,
    val skipBalanceUpdate: Boolean? = null,
)
