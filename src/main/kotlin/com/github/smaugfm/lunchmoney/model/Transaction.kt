package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import com.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

@Serializable
data class Transaction(
    val id: Long,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    val payee: String,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency,
    val toBase: Double,
    val notes: String?,
    val categoryId: Long?,
    val assetId: Long?,
    val recurringId: Long?,
    val plaidAccountId: Long?,
    @Serializable(with = TransactionStatusSerializer::class)
    val status: TransactionStatus,
    val parentId: Long?,
    val isGroup: Boolean,
    val groupId: Long?,
    val externalId: String?,
    val tags: List<Tag>?,
    val originalName: String?,
    val type: String?,
    val subtype: String?,
    val fees: String?,
    val price: String?,
    val quantity: String?
)
