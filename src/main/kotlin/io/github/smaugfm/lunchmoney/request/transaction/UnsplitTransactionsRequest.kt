package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.UnsplitTransactionsParams

internal class UnsplitTransactionsRequest(params: UnsplitTransactionsParams) :
    LunchmoneyAbstractPostRequest<List<Long>, UnsplitTransactionsParams>(
        PathAndQuery.segment("transactions").segment("unsplit"),
        params
    )
