package io.github.smaugfm.lunchmoney.request.tag

import io.github.smaugfm.lunchmoney.helper.PathAndQuery
import io.github.smaugfm.lunchmoney.model.Tag
import io.github.smaugfm.lunchmoney.request.base.GetRequest

class GetAllTagsRequest :
    GetRequest<List<Tag>>(PathAndQuery.segment("tags"))
