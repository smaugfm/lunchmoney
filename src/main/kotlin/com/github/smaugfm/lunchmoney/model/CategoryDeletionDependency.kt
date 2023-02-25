package com.github.smaugfm.lunchmoney.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDeletionDependency(
    val categoryName: String,
    val budget: Long,
    val categoryRules: Long,
    val transactions: Long,
    val children: Long,
    val recurring: Long
)
