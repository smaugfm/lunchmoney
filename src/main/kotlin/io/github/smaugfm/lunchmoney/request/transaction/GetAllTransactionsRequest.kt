package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import io.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse

class GetAllTransactionsRequest(params: GetAllTransactionsParams? = null) :
    GetRequest<GetAllTransactionsResponse>(
        PathAndQuery
            .segment("transactions")
            .query(params)
    )
