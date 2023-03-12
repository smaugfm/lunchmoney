package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.DeleteRequest
import io.github.smaugfm.lunchmoney.response.DeleteTransactionGroupResponse

class DeleteTransactionGroupRequest(transactionId: Long) :
    DeleteRequest<DeleteTransactionGroupResponse>(
        PathAndQuery.segment("transactions").segment("group").segment(transactionId)
    )
