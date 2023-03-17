package io.github.smaugfm.lunchmoney.request.tag

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionTag
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest

class LunchmoneyGetAllTagsRequest :
    LunchmoneyAbstractGetRequest<List<LunchmoneyTransactionTag>>(PathAndQuery.segment("tags"))
