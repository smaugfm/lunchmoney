package io.github.smaugfm.lunchmoney.request.budget

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.DeleteRequest
import io.github.smaugfm.lunchmoney.request.budget.params.RemoveBudgetParams

class RemoveBudgetRequest(params: RemoveBudgetParams) :
    DeleteRequest<Boolean>(
        PathAndQuery.segment("budgets").query(params)
    )
