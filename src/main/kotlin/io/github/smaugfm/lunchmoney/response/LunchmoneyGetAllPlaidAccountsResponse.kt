package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyPlaidAccount
import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyGetAllPlaidAccountsResponse(
    val plaidAccounts: List<LunchmoneyPlaidAccount>
)
