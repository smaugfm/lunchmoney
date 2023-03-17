package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertOrUpdateTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionSplit
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyUpdateTransactionParams(
    val transaction: LunchmoneyInsertOrUpdateTransaction,
    val split: LunchmoneyTransactionSplit? = null,
    val debitAsNegative: Boolean? = null,
    val skipBalanceUpdate: Boolean? = null
)
