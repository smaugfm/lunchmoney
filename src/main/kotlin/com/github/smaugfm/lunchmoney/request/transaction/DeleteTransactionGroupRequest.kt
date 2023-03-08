package com.github.smaugfm.lunchmoney.request.transaction

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.DeleteRequest
import com.github.smaugfm.lunchmoney.response.DeleteTransactionGroupResponse

class DeleteTransactionGroupRequest(transactionId: Long) :
    DeleteRequest<DeleteTransactionGroupResponse>(
        PathAndQuery.segment("transactions").segment("group").segment(transactionId)
    )
