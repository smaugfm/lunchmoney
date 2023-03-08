package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.UnsplitTransactionsParams
import reactor.core.publisher.Mono

class UnsplitTransactionsRequest(params: Mono<UnsplitTransactionsParams>) :
    PostRequest<List<Long>, UnsplitTransactionsParams>(
        PathAndQuery.segment("transactions").segment("unsplit"),
        params
    ) {
    constructor(params: UnsplitTransactionsParams) : this(Mono.just(params))
}
