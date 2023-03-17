package io.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyUpdateTransactionResponse(
    val updated: Boolean,
    val split: List<Long>? = null
)
