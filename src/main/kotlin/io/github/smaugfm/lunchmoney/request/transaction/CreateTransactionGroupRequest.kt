package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams
import reactor.core.publisher.Mono

class CreateTransactionGroupRequest(params: Mono<CreateTransactionGroupParams>) :
    PostRequest<Long, CreateTransactionGroupParams>(
        PathAndQuery.segment("transactions").segment("group"),
        params
    ) {
    constructor(params: CreateTransactionGroupParams) :
        this(Mono.just(params))
}
