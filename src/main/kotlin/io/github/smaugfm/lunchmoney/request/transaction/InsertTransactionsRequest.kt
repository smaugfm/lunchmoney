package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import io.github.smaugfm.lunchmoney.response.InsertTransactionsResponse
import reactor.core.publisher.Mono

class InsertTransactionsRequest(params: Mono<InsertTransactionRequestParams>) :
    PostRequest<InsertTransactionsResponse, InsertTransactionRequestParams>(
        PathAndQuery.segment("transactions"),
        params
    ) {
    constructor(params: InsertTransactionRequestParams) : this(Mono.just(params))
}
