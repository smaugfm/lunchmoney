package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import com.github.smaugfm.lunchmoney.response.InsertTransactionsResponse
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class InsertTransactionsRequest(private val params: Mono<InsertTransactionRequestParams>) :
    ApiRequest<InsertTransactionsResponse, InsertTransactionRequestParams>() {
    constructor(params: InsertTransactionRequestParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("transactions")

    override fun method(): HttpMethod = HttpMethod.POST

    override fun body(): Mono<InsertTransactionRequestParams> = params
}
