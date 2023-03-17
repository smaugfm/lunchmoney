package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyInsertTransactionRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyInsertTransactionsResponse
import reactor.core.publisher.Mono

class LunchmoneyInsertTransactionsRequest(params: Mono<LunchmoneyInsertTransactionRequestParams>) :
    LunchmoneyAbstractPostRequest<LunchmoneyInsertTransactionsResponse, LunchmoneyInsertTransactionRequestParams>(
        PathAndQuery.segment("transactions"),
        params
    ) {
    constructor(params: LunchmoneyInsertTransactionRequestParams) : this(Mono.just(params))
}
