package io.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class LunchmoneyUser(
    val userId: Long,
    val userName: String,
    val userEmail: String,
    val accountId: Long,
    val budgetName: String,
    val apiKeyLabel: String? = null
)
