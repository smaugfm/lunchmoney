package io.github.smaugfm.lunchmoney.request.transaction.params

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyUnsplitTransactionsParams(
    val parentIds: List<Long>,
    val removeParents: Boolean? = null
)
