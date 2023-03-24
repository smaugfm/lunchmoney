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
data class LunchmoneyInsertTransaction(
    val date: LocalDate,
    val amount: BigDecimal,
    val categoryId: Long? = null,
    val payee: String? = null,
    val currency: Currency? = null,
    val assetId: Long? = null,
    val recurringId: Long? = null,
    val notes: String? = null,
    val status: LunchmoneyTransactionStatus? = null,
    val externalId: String? = null,
    val tags: List<LunchmoneyTransactionTag>? = null
)
