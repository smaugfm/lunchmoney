package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Budget
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams

class GetBudgetSummaryRequest(params: GetBudgetSummaryParams) :
    GetRequest<List<Budget>>(
        PathAndQuery.segment("budgets").query(params)
    )
