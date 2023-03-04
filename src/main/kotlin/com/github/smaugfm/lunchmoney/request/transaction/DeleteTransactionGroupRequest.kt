package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.response.DeleteTransactionGroupResponse
import io.netty.handler.codec.http.HttpMethod

class DeleteTransactionGroupRequest(transactionId: Long) :
    ApiRequest<DeleteTransactionGroupResponse, Unit>() {
    override val pathAndQuery =
        PathAndQuery.segment("transactions").segment("group").segment(transactionId)

    override fun method(): HttpMethod = HttpMethod.DELETE
}
