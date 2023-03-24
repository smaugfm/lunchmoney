package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import io.github.smaugfm.lunchmoney.response.InsertTransactionsResponse

internal class InsertTransactionsRequest(
    params: InsertTransactionRequestParams
) :
    LunchmoneyAbstractPostRequest<
            InsertTransactionsResponse,
            InsertTransactionRequestParams>
        (
        PathAndQuery.segment("transactions"),
        params
    )
