package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Transaction
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams
import io.netty.handler.codec.http.HttpMethod

class GetSingleTransactionRequest(
    id: Long,
    params: GetSingleTransactionParams? = null
) : ApiRequest<Transaction, GetSingleTransactionParams>() {
    override val pathAndQuery =
        PathAndQuery
            .segment("transactions")
            .segment(id)
            .query(params)

    override fun method(): HttpMethod = HttpMethod.GET
}
