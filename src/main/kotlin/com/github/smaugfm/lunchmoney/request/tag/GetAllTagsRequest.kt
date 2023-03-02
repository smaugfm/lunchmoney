package com.github.smaugfm.lunchmoney.request.tag

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Tag
import com.github.smaugfm.lunchmoney.request.ApiRequest
import io.netty.handler.codec.http.HttpMethod

class GetAllTagsRequest : ApiRequest<List<Tag>, Unit>() {

    override val pathAndQuery = PathAndQuery.segment("tags")
    override fun method(): HttpMethod = HttpMethod.GET
}
