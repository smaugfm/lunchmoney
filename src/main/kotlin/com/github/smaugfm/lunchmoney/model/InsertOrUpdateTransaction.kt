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
data class InsertOrUpdateTransaction(
    val date: LocalDate,
    val amount: BigDecimal,
    val categoryId: Long? = null,
    val payee: String? = null,
    val currency: Currency? = null,
    val assetId: Long? = null,
    val recurringId: Long? = null,
    val notes: String? = null,
    val status: TransactionStatus? = null,
    val externalId: String? = null,
    val tags: List<Tag>? = null
)
