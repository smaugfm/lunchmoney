package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPutRequest
import io.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import io.github.smaugfm.lunchmoney.response.UpsertBudgetResponse

internal class UpsertBudgetRequest(params: UpsertBudgetRequestParams) :
    LunchmoneyAbstractPutRequest<UpsertBudgetResponse, UpsertBudgetRequestParams>(
        PathAndQuery.segment("budgets"),
        params
    )
