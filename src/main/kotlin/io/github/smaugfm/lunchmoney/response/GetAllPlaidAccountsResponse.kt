package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyPlaidAccount
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllPlaidAccountsResponse(
    val plaidAccounts: List<LunchmoneyPlaidAccount>
)
