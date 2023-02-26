package com.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val accountId: Long,
    val budgetName: String,
    val apiKeyLabel: String?
)
