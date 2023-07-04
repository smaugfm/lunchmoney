@file:UseSerializers(
    RecurringExpenseSourceSerializer::class,
    RecurringExpenseTypeSerializer::class,
    InstantSerializer::class,
    LocalDateSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseSource
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseType
import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import io.github.smaugfm.lunchmoney.serializer.RecurringExpenseSourceSerializer
import io.github.smaugfm.lunchmoney.serializer.RecurringExpenseTypeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

@Serializable
data class LunchmoneyRecurringExpense(
    val id: Long,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val cadence: String,
    val payee: String,
    val amount: BigDecimal,
    val currency: Currency,
    val createdAt: Instant,
    val description: String? = null,
    val billingDate: LocalDate,
    val type: LunchmoneyRecurringExpenseType,
    val originalName: String? = null,
    val source: LunchmoneyRecurringExpenseSource? = null,
    val plaidAccountId: Long? = null,
    val assetId: Long? = null,
    val transactionId: Long? = null,
    val categoryId: Long? = null
)
