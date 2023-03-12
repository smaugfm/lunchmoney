package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.PlaidAccount
import kotlinx.serialization.Serializable

@Serializable
data class GetAllPlaidAccountsResponse(
    val plaidAccounts: List<PlaidAccount>
)
