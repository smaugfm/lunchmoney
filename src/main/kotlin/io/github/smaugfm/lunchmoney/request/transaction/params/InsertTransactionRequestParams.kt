package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import kotlinx.serialization.Serializable

@Serializable
internal data class InsertTransactionRequestParams(
    val transactions: List<LunchmoneyInsertTransaction>,
    val applyRules: Boolean?,
    val skipDuplicates: Boolean?,
    val checkForRecurring: Boolean?,
    val debitAsNegative: Boolean?,
    val skipBalanceUpdate: Boolean?,
)
