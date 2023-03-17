package io.github.smaugfm.lunchmoney.request.plaid

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetAllPlaidAccountsResponse

class LunchmoneyGetAllPlaidAccountsRequest : LunchmoneyAbstractGetRequest<LunchmoneyGetAllPlaidAccountsResponse>(
    PathAndQuery.segment("plaid_accounts")
)
