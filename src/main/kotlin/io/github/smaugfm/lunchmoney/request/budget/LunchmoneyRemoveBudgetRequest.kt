package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractDeleteRequest
import io.github.smaugfm.lunchmoney.request.budget.params.LunchmoneyRemoveBudgetParams

class LunchmoneyRemoveBudgetRequest(params: LunchmoneyRemoveBudgetParams) :
    LunchmoneyAbstractDeleteRequest<Boolean>(
        PathAndQuery.segment("budgets").query(params)
    )
