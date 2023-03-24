package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyInsertTransactionRequestParams(
    val transactions: List<LunchmoneyInsertTransaction>,
    val applyRules: Boolean? = null,
    val skipDuplicates: Boolean? = null,
    val checkForRecurring: Boolean? = null,
    val debitAsNegative: Boolean? = null,
    val skipBalanceUpdate: Boolean? = null
)
