package io.github.smaugfm.lunchmoney.request.transaction

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractPostRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams

internal class CreateTransactionGroupRequest(params: CreateTransactionGroupParams) :
    LunchmoneyAbstractPostRequest<Long, CreateTransactionGroupParams>(
        PathAndQuery.segment("transactions").segment("group"),
        params
    )
