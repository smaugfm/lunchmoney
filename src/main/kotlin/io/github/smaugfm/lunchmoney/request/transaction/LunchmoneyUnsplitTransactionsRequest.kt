package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyUnsplitTransactionsParams
import reactor.core.publisher.Mono

class LunchmoneyUnsplitTransactionsRequest(params: Mono<LunchmoneyUnsplitTransactionsParams>) :
    LunchmoneyAbstractPostRequest<List<Long>, LunchmoneyUnsplitTransactionsParams>(
        PathAndQuery.segment("transactions").segment("unsplit"),
        params
    ) {
    constructor(params: LunchmoneyUnsplitTransactionsParams) : this(Mono.just(params))
}
