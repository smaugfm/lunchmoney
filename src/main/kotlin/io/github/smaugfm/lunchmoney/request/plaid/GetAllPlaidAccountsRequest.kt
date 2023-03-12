package io.github.smaugfm.lunchmoney.request.plaid

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.response.GetAllPlaidAccountsResponse

class GetAllPlaidAccountsRequest : GetRequest<GetAllPlaidAccountsResponse>(
    PathAndQuery.segment("plaid_accounts")
)
