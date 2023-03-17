package io.github.smaugfm.lunchmoney.request.recurring

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest
import io.github.smaugfm.lunchmoney.request.recurring.params.LunchmoneyGetRecurringExpensesParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetRecurringExpensesResponse

class LunchmoneyGetRecurringExpensesRequest(params: LunchmoneyGetRecurringExpensesParams? = null) :
    LunchmoneyAbstractGetRequest<LunchmoneyGetRecurringExpensesResponse>(
        PathAndQuery.segment("recurring_expenses").query(params)
    )
