package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class CreateTransactionGroupRequest(private val params: Mono<CreateTransactionGroupParams>) :
    ApiRequest<Long, CreateTransactionGroupParams>() {

    constructor(params: CreateTransactionGroupParams)
            : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("transactions").segment("group")

    override fun method(): HttpMethod = HttpMethod.POST

    override fun body(): Mono<CreateTransactionGroupParams> = params

}
