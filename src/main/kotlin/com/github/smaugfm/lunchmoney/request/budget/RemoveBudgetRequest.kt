package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.DeleteRequest
import com.github.smaugfm.lunchmoney.request.budget.params.RemoveBudgetParams

class RemoveBudgetRequest(params: RemoveBudgetParams) :
    DeleteRequest<Boolean>(
        PathAndQuery.segment("budgets").query(params)
    )
