package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractDeleteRequest
import io.github.smaugfm.lunchmoney.response.DeleteTransactionGroupResponse

internal class DeleteTransactionGroupRequest(id: Long) :
    LunchmoneyAbstractDeleteRequest<DeleteTransactionGroupResponse>(
        PathAndQuery.segment("transactions").segment("group").segment(id)
    )
