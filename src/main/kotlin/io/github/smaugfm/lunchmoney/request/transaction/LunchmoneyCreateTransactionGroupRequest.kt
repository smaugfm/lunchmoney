package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyLunchmoneyCreateTransactionGroupParams
import reactor.core.publisher.Mono

class LunchmoneyCreateTransactionGroupRequest(params: Mono<LunchmoneyLunchmoneyCreateTransactionGroupParams>) :
    LunchmoneyAbstractPostRequest<Long, LunchmoneyLunchmoneyCreateTransactionGroupParams>(
        PathAndQuery.segment("transactions").segment("group"),
        params
    ) {
    constructor(params: LunchmoneyLunchmoneyCreateTransactionGroupParams) :
        this(Mono.just(params))
}
