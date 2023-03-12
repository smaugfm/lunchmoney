package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.PutRequest
import io.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import io.github.smaugfm.lunchmoney.response.UpsertBudgetResponse
import reactor.core.publisher.Mono

class UpsertBudgetRequest(params: Mono<UpsertBudgetRequestParams>) :
    PutRequest<UpsertBudgetResponse, UpsertBudgetRequestParams>(
        PathAndQuery.segment("budgets"),
        params
    ) {
    constructor(params: UpsertBudgetRequestParams) : this(Mono.just(params))
}
