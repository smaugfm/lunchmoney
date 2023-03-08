package com.github.smaugfm.lunchmoney.request.recurring

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.GetRequest
import com.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import com.github.smaugfm.lunchmoney.response.GetRecurringExpensesResponse

class GetRecurringExpensesRequest(params: GetRecurringExpensesParams? = null) :
    GetRequest<GetRecurringExpensesResponse>(
        PathAndQuery.segment("recurring_expenses").query(params)
    )
