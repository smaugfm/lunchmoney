package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PutRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.UpdateTransactionParams
import io.github.smaugfm.lunchmoney.response.UpdateTransactionResponse
import reactor.core.publisher.Mono

class UpdateTransactionRequest(
    transactionId: Long,
    params: Mono<UpdateTransactionParams>
) : PutRequest<UpdateTransactionResponse, UpdateTransactionParams>(
    PathAndQuery.segment("transactions").segment(transactionId),
    params
) {
    constructor(transactionId: Long, params: UpdateTransactionParams) :
        this(transactionId, Mono.just(params))
}
