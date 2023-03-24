package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.UpdateTransactionParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyUpdateTransactionResponse

internal class UpdateTransactionRequest(
    transactionId: Long,
    params: UpdateTransactionParams
) : LunchmoneyAbstractPutRequest<LunchmoneyUpdateTransactionResponse, UpdateTransactionParams>(
    PathAndQuery.segment("transactions").segment(transactionId),
    params
)
