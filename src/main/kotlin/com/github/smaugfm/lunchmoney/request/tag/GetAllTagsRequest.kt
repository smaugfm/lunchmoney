package com.github.smaugfm.lunchmoney.request.tag

import com.github.smaugfm.lunchmoney.helper.PathAndQuery
import com.github.smaugfm.lunchmoney.model.Tag
import com.github.smaugfm.lunchmoney.request.base.GetRequest

class GetAllTagsRequest :
    GetRequest<List<Tag>>(PathAndQuery.segment("tags"))
