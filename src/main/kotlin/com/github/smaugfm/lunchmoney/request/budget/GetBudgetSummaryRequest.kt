package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Budget
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams
import io.netty.handler.codec.http.HttpMethod

class GetBudgetSummaryRequest(params: GetBudgetSummaryParams) :
    ApiRequest<List<Budget>, GetBudgetSummaryParams>() {
    override val pathAndQuery = PathAndQuery.segment("budgets").query(params)

    override fun method(): HttpMethod = HttpMethod.GET
}
