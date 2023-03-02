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
data class InsertableTransaction(
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    val categoryId: Long? = null,
    val payee: String? = null,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency? = null,
    val assetId: Long? = null,
    val recurringId: Long? = null,
    val notes: String? = null,
    @Serializable(with = TransactionStatusSerializer::class)
    val status: TransactionStatus? = null,
    val externalId: String? = null,
    val tags: List<Tag>? = null
)
