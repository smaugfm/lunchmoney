package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Budget
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams

class GetBudgetSummaryRequest(params: GetBudgetSummaryParams) :
    GetRequest<List<Budget>>(
        PathAndQuery.segment("budgets").query(params)
    )
