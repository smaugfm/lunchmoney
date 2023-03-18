package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyCreateTransactionGroupParams
import reactor.core.publisher.Mono

class LunchmoneyCreateTransactionGroupRequest(params: Mono<LunchmoneyCreateTransactionGroupParams>) :
    LunchmoneyAbstractPostRequest<Long, LunchmoneyCreateTransactionGroupParams>(
        PathAndQuery.segment("transactions").segment("group"),
        params
    ) {
    constructor(params: LunchmoneyCreateTransactionGroupParams) :
        this(Mono.just(params))
}
