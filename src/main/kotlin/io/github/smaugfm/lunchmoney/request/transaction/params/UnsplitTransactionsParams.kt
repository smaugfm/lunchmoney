package io.github.smaugfm.lunchmoney.request.transaction.params

import kotlinx.serialization.Serializable

@Serializable
internal data class UnsplitTransactionsParams(
    val parentIds: List<Long>,
    val removeParents: Boolean?
)
