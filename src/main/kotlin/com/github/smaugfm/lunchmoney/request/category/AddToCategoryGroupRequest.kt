package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.PathAndQuery
import com.github.smaugfm.lunchmoney.model.CategorySingle
import com.github.smaugfm.lunchmoney.request.ApiRequest
import com.github.smaugfm.lunchmoney.request.category.params.AddToCategoryGroupsParams
import io.netty.handler.codec.http.HttpMethod
import reactor.core.publisher.Mono

class AddToCategoryGroupRequest(
    groupId: Long,
    private val params: Mono<AddToCategoryGroupsParams>
) : ApiRequest<CategorySingle, AddToCategoryGroupsParams>() {

    constructor(groupId: Long, params: AddToCategoryGroupsParams) : this(groupId, Mono.just(params))

    override val pathAndQuery = PathAndQuery
        .segment("categories")
        .segment("group")
        .segment(groupId)
        .segment("add")

    override fun method(): HttpMethod = HttpMethod.POST
    override fun body() = params
}
