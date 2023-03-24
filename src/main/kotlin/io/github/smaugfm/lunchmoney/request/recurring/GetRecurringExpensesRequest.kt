package io.github.smaugfm.lunchmoney.request.recurring

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import io.github.smaugfm.lunchmoney.response.GetRecurringExpensesResponse

internal class GetRecurringExpensesRequest(params: GetRecurringExpensesParams) :
    LunchmoneyAbstractGetRequest<GetRecurringExpensesResponse>(
        PathAndQuery.segment("recurring_expenses").query(params)
    )
