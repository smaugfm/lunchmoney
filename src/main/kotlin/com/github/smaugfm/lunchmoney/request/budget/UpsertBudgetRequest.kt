package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import com.github.smaugfm.lunchmoney.response.UpsertBudgetResponse
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class UpsertBudgetRequest(private val params: Mono<UpsertBudgetRequestParams>) :
    ApiRequest<UpsertBudgetResponse, UpsertBudgetRequestParams>() {

    constructor(params: UpsertBudgetRequestParams) : this(Mono.just(params))

    override val pathAndQuery = PathAndQuery.segment("budgets")

    override fun method(): HttpMethod = HttpMethod.PUT

    override fun body(): Mono<UpsertBudgetRequestParams> = params
}
