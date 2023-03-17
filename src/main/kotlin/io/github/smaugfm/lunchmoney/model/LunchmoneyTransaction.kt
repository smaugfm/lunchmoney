@file:UseSerializers(
    TransactionStatusSerializer::class,
    LocalDateSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import io.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

@Serializable
data class LunchmoneyTransaction(
    val id: Long,
    val date: LocalDate,
    val payee: String,
    val amount: BigDecimal,
    val currency: Currency,
    val toBase: Double,
    val notes: String? = null,
    val categoryId: Long? = null,
    val assetId: Long? = null,
    val recurringId: Long? = null,
    val plaidAccountId: Long? = null,
    val status: LunchmoneyTransactionStatus,
    val parentId: Long? = null,
    val isGroup: Boolean,
    val groupId: Long? = null,
    val externalId: String? = null,
    val tags: List<LunchmoneyTransactionTag>? = null,
    val originalName: String? = null,
    val type: String? = null,
    val subtype: String? = null,
    val fees: String? = null,
    val price: String? = null,
    val quantity: String? = null
)
