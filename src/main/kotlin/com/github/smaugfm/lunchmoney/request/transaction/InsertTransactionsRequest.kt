package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import com.github.smaugfm.lunchmoney.response.InsertTransactionsResponse
import reactor.core.publisher.Mono

class InsertTransactionsRequest(params: Mono<InsertTransactionRequestParams>) :
    PostRequest<InsertTransactionsResponse, InsertTransactionRequestParams>(
        PathAndQuery.segment("transactions"),
        params
    ) {
    constructor(params: InsertTransactionRequestParams) : this(Mono.just(params))
}
