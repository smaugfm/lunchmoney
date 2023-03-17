package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyGetAllTransactionsParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetAllTransactionsResponse

class LunchmoneyGetAllTransactionsRequest(params: LunchmoneyGetAllTransactionsParams? = null) :
    LunchmoneyAbstractGetRequest<LunchmoneyGetAllTransactionsResponse>(
        PathAndQuery
            .segment("transactions")
            .query(params)
    )
