package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.model.PlaidAccount
import kotlinx.serialization.Serializable

@Serializable
data class GetAllPlaidAccountsResponse(
    val plaidAccounts: List<PlaidAccount>
)
