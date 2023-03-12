package io.github.smaugfm.lunchmoney.request.recurring

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.GetRequest
import io.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import io.github.smaugfm.lunchmoney.response.GetRecurringExpensesResponse

class GetRecurringExpensesRequest(params: GetRecurringExpensesParams? = null) :
    GetRequest<GetRecurringExpensesResponse>(
        PathAndQuery.segment("recurring_expenses").query(params)
    )
