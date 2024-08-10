@file:UseSerializers(
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.Currency

@Serializable
data class LunchmoneyUser(
    val userId: Long,
    val userName: String,
    val userEmail: String,
    val accountId: Long,
    val budgetName: String,
    val primaryCurrency: Currency,
    val apiKeyLabel: String? = null
)
