package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionSplit
import io.github.smaugfm.lunchmoney.model.LunchmoneyUpdateTransaction
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyUpdateTransactionParams(
    val transaction: LunchmoneyUpdateTransaction,
    val split: LunchmoneyTransactionSplit? = null,
    val debitAsNegative: Boolean? = null,
    val skipBalanceUpdate: Boolean? = null
)
