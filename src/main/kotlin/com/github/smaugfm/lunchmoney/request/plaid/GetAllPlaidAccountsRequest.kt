package com.github.smaugfm.lunchmoney.request.plaid

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.response.GetAllPlaidAccountsResponse

class GetAllPlaidAccountsRequest : GetRequest<GetAllPlaidAccountsResponse>(
    PathAndQuery.segment("plaid_accounts")
)
