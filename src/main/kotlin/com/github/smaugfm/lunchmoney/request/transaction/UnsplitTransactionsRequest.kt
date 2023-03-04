package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.UnsplitTransactionsParams
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class UnsplitTransactionsRequest(private val params: Mono<UnsplitTransactionsParams>) :
    ApiRequest<List<Long>, UnsplitTransactionsParams>() {

    constructor(params: UnsplitTransactionsParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("transactions").segment("unsplit")

    override fun method(): HttpMethod = HttpMethod.POST

    override fun body(): Mono<UnsplitTransactionsParams> = params
}
