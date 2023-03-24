package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import io.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse

internal class GetAllTransactionsRequest(params: GetAllTransactionsParams?) :
    LunchmoneyAbstractGetRequest<GetAllTransactionsResponse>(
        PathAndQuery
            .segment("transactions")
            .query(params)
    )
