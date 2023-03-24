package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams

internal class GetSingleTransactionRequest(
    id: Long,
    params: GetSingleTransactionParams? = null
) : LunchmoneyAbstractGetRequest<LunchmoneyTransaction>(
    PathAndQuery
        .segment("transactions")
        .segment(id)
        .query(params)
)
