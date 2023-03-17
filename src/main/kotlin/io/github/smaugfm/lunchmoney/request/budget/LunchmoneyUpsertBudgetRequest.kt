package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.budget.params.LunchmoneyUpsertBudgetRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyUpsertBudgetResponse
import reactor.core.publisher.Mono

class LunchmoneyUpsertBudgetRequest(params: Mono<LunchmoneyUpsertBudgetRequestParams>) :
    LunchmoneyAbstractPutRequest<LunchmoneyUpsertBudgetResponse, LunchmoneyUpsertBudgetRequestParams>(
        PathAndQuery.segment("budgets"),
        params
    ) {
    constructor(params: LunchmoneyUpsertBudgetRequestParams) : this(Mono.just(params))
}
