package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseSource
import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseType
import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import com.github.smaugfm.lunchmoney.serializer.InstantSerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import com.github.smaugfm.lunchmoney.serializer.RecurringExpenseSourceSerializer
import com.github.smaugfm.lunchmoney.serializer.RecurringExpenseTypeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

@Serializable
data class RecurringExpense(
    val id: Long,
    @Serializable(with = LocalDateSerializer::class)
    val startDate: LocalDate,
    @Serializable(with = LocalDateSerializer::class)
    val endDate: LocalDate? = null,
    val cadence: String,
    val payee: String,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant,
    val description: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    val billingDate: LocalDate,
    @Serializable(with = RecurringExpenseTypeSerializer::class)
    val type: RecurringExpenseType,
    val originalName: String? = null,
    @Serializable(with = RecurringExpenseSourceSerializer::class)
    val source: RecurringExpenseSource? = null,
    val plaidAccountId: Long? = null,
    val assetId: Long? = null,
    val transactionId: Long? = null,
    val categoryId: Long? = null,
)
