@file:UseSerializers(
    TransactionStatusSerializer::class,
    LocalDateSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class
)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus
import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import com.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

@Serializable
data class Transaction(
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
    val status: TransactionStatus,
    val parentId: Long? = null,
    val isGroup: Boolean,
    val groupId: Long? = null,
    val externalId: String? = null,
    val tags: List<Tag>? = null,
    val originalName: String? = null,
    val type: String? = null,
    val subtype: String? = null,
    val fees: String? = null,
    val price: String? = null,
    val quantity: String? = null
)
