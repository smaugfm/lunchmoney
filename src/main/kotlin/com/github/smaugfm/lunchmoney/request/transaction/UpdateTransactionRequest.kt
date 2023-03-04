package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.UpdateTransactionParams
import com.github.smaugfm.lunchmoney.response.UpdateTransactionResponse
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class UpdateTransactionRequest(
    transactionId: Long,
    private val params: Mono<UpdateTransactionParams>
) :
    ApiRequest<UpdateTransactionResponse, UpdateTransactionParams>() {

    constructor(transactionId: Long, params: UpdateTransactionParams)
            : this(transactionId, Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("transactions").segment(transactionId)

    override fun method(): HttpMethod = HttpMethod.PUT

    override fun body(): Mono<UpdateTransactionParams> = params
}
