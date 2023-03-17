package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudget
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.budget.params.LunchmoneyGetBudgetSummaryParams

class LunchmoneyGetBudgetSummaryRequest(params: LunchmoneyGetBudgetSummaryParams) :
    LunchmoneyAbstractGetRequest<List<LunchmoneyBudget>>(
        PathAndQuery.segment("budgets").query(params)
    )
