package com.github.smaugfm.lunchmoney.request.user

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.User
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class GetCurrentUserRequest : ApiRequest<User, Unit>() {

    override val pathAndQuery = PathAndQuery.segment("me")
    override fun method(): HttpMethod = HttpMethod.GET
}
