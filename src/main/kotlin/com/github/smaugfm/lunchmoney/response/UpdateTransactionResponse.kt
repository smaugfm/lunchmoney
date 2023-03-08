package com.github.smaugfm.lunchmoney.response

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTransactionResponse(
    val updated: Boolean,
    val split: List<Long>? = null
)
