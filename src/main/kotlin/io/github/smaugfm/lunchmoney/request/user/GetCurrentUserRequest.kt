package io.github.smaugfm.lunchmoney.request.user

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.User
import io.github.smaugfm.lunchmoney.request.base.GetRequest

class GetCurrentUserRequest : GetRequest<User>(
    PathAndQuery.segment("me")
)
