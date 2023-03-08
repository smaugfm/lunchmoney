package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams
import reactor.core.publisher.Mono

class CreateTransactionGroupRequest(params: Mono<CreateTransactionGroupParams>) :
    PostRequest<Long, CreateTransactionGroupParams>(
        PathAndQuery.segment("transactions").segment("group"),
        params
    ) {
    constructor(params: CreateTransactionGroupParams) :
        this(Mono.just(params))
}
