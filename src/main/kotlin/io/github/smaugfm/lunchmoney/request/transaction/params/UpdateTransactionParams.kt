package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionSplit
import io.github.smaugfm.lunchmoney.model.LunchmoneyUpdateTransaction
import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateTransactionParams(
    val transaction: LunchmoneyUpdateTransaction,
    val split: LunchmoneyTransactionSplit?,
    val debitAsNegative: Boolean?,
    val skipBalanceUpdate: Boolean?,
)
