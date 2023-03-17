package io.github.smaugfm.lunchmoney.request.user

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest

class LunchmoneyGetCurrentUserRequest : LunchmoneyAbstractGetRequest<LunchmoneyUser>(
    PathAndQuery.segment("me")
)
