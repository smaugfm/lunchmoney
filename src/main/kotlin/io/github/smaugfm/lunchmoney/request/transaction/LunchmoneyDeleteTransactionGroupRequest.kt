package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractDeleteRequest
import io.github.smaugfm.lunchmoney.response.LunchmoneyDeleteTransactionGroupResponse

class LunchmoneyDeleteTransactionGroupRequest(transactionId: Long) :
    LunchmoneyAbstractDeleteRequest<LunchmoneyDeleteTransactionGroupResponse>(
        PathAndQuery.segment("transactions").segment("group").segment(transactionId)
    )
