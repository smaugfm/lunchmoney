package com.github.smaugfm.lunchmoney.request.user

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.User
import com.github.smaugfm.lunchmoney.request.base.GetRequest

class GetCurrentUserRequest : GetRequest<User>(
    PathAndQuery.segment("me")
)
