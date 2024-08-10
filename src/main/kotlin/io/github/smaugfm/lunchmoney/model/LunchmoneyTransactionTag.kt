package io.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyTransactionTag(
    val id: Long,
    val name: String,
    val description: String? = null,
    val archived: Boolean? = null,
)
