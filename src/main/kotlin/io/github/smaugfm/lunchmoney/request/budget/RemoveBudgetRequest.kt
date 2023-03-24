package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractDeleteRequest
import io.github.smaugfm.lunchmoney.request.budget.params.RemoveBudgetParams

internal class RemoveBudgetRequest(params: RemoveBudgetParams) :
    LunchmoneyAbstractDeleteRequest<Boolean>(
        PathAndQuery.segment("budgets").query(params)
    )
