package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudget
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams

internal class GetBudgetSummaryRequest(params: GetBudgetSummaryParams) :
    LunchmoneyAbstractGetRequest<List<LunchmoneyBudget>>(
        PathAndQuery.segment("budgets").query(params)
    )
