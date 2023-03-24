package io.github.smaugfm.lunchmoney.request.user

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import io.github.smaugfm.lunchmoney.request.base.LunchmoneyAbstractGetRequest

internal class GetCurrentUserRequest : LunchmoneyAbstractGetRequest<LunchmoneyUser>(
    PathAndQuery.segment("me")
)
