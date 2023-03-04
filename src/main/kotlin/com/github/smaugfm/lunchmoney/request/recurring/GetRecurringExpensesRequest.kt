package com.github.smaugfm.lunchmoney.request.recurring

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import com.github.smaugfm.lunchmoney.response.GetRecurringExpensesResponse
import io.netty.handler.codec.http.HttpMethod

class GetRecurringExpensesRequest(params: GetRecurringExpensesParams? = null) :
    ApiRequest<GetRecurringExpensesResponse, Unit>() {
    override val pathAndQuery = PathAndQuery.segment("recurring_expenses").query(params)

    override fun method(): HttpMethod = HttpMethod.GET
}
