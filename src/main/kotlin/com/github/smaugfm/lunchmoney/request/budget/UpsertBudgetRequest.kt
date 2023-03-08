package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PutRequest
import com.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import com.github.smaugfm.lunchmoney.response.UpsertBudgetResponse
import reactor.core.publisher.Mono

class UpsertBudgetRequest(params: Mono<UpsertBudgetRequestParams>) :
    PutRequest<UpsertBudgetResponse, UpsertBudgetRequestParams>(
        PathAndQuery.segment("budgets"),
        params
    ) {
    constructor(params: UpsertBudgetRequestParams) : this(Mono.just(params))
}
