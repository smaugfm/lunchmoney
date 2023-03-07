package com.github.smaugfm.lunchmoney.request.budget

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.budget.params.RemoveBudgetParams
import io.netty.handler.codec.http.HttpMethod

class RemoveBudgetRequest(params: RemoveBudgetParams) : ApiRequest<Boolean, RemoveBudgetParams>() {
    override val pathAndQuery = PathAndQuery.segment("budgets").query(params)

    override fun method(): HttpMethod = HttpMethod.DELETE
}
