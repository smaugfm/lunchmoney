package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import com.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse
import io.netty.handler.codec.http.HttpMethod

class GetAllTransactionsRequest(
    params: GetAllTransactionsParams?
) : ApiRequest<GetAllTransactionsResponse, GetAllTransactionsParams>() {
    override val pathAndQuery = PathAndQuery
        .segment("transactions")
        .query(params)

    override fun method(): HttpMethod = HttpMethod.GET
}
