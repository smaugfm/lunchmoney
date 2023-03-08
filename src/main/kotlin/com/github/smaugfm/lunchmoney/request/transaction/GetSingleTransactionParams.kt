package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Transaction
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams

class GetSingleTransactionRequest(
    id: Long,
    params: GetSingleTransactionParams? = null
) : GetRequest<Transaction>(
    PathAndQuery
        .segment("transactions")
        .segment(id)
        .query(params)
)
