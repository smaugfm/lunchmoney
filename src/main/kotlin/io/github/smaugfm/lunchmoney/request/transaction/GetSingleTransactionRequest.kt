package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Transaction
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams

class GetSingleTransactionRequest(
    id: Long,
    params: GetSingleTransactionParams? = null
) : GetRequest<Transaction>(
    PathAndQuery
        .segment("transactions")
        .segment(id)
        .query(params)
)
