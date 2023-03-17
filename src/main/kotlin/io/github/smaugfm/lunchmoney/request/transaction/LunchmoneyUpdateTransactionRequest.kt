package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyUpdateTransactionParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyUpdateTransactionResponse
import reactor.core.publisher.Mono

class LunchmoneyUpdateTransactionRequest(
    transactionId: Long,
    params: Mono<LunchmoneyUpdateTransactionParams>
) : LunchmoneyAbstractPutRequest<LunchmoneyUpdateTransactionResponse, LunchmoneyUpdateTransactionParams>(
    PathAndQuery.segment("transactions").segment(transactionId),
    params
) {
    constructor(transactionId: Long, params: LunchmoneyUpdateTransactionParams) :
        this(transactionId, Mono.just(params))
}
