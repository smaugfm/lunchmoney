package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import com.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse

class GetAllTransactionsRequest(params: GetAllTransactionsParams? = null) :
    GetRequest<GetAllTransactionsResponse>(
        PathAndQuery
            .segment("transactions")
            .query(params)
    )
