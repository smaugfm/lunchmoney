package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.request.base.PostRequest
import com.github.smaugfm.lunchmoney.request.category.params.CreateCategoryGroupRequestParams
import com.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import reactor.core.publisher.Mono

class CreateCategoryGroupRequest(params: Mono<CreateCategoryGroupRequestParams>) :
    PostRequest<CreateCategoryResponse, CreateCategoryGroupRequestParams>(
        PathAndQuery.segment("categories").segment("group"),
        params
    ) {
    constructor(params: CreateCategoryGroupRequestParams) : this(Mono.just(params))
}
