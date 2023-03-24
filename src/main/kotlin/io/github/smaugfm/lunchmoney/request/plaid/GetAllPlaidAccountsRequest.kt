package io.github.smaugfm.lunchmoney.request.plaid

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.GetAllPlaidAccountsResponse

internal class GetAllPlaidAccountsRequest :
    LunchmoneyAbstractGetRequest<GetAllPlaidAccountsResponse>(
        PathAndQuery.segment("plaid_accounts")
    )
