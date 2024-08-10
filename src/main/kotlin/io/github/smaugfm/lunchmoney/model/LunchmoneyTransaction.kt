@file:UseSerializers(
    TransactionStatusSerializer::class,
    LocalDateSerializer::class,
    BigDecimalSerializer::class,
    CurrencySerializer::class,
    InstantSerializer::class,
    AssetStatusSerializer::class,
    RecurringExpenseTypeSerializer::class,
    TransactionSourceSerializer::class
)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetStatus
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseType
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionSource
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.serializer.AssetStatusSerializer
import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import io.github.smaugfm.lunchmoney.serializer.RecurringExpenseTypeSerializer
import io.github.smaugfm.lunchmoney.serializer.TransactionSourceSerializer
import io.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant
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
    val categoryId: Long? = null,
    val categoryName: String? = null,
    val categoryGroupId: Long? = null,
    val categoryGroupName: String? = null,
    val isIncome: Boolean,
    val excludeFromBudget: Boolean,
    val excludeFromTotals: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
    val status: LunchmoneyTransactionStatus? = null,
    val isPending: Boolean,
    val notes: String? = null,
    val originalName: String? = null,
    val recurringId: Long? = null,
    val recurringPayee: String? = null,
    val recurringDescription: String? = null,
    val recurringCadence: String? = null,
    val recurringType: LunchmoneyRecurringExpenseType? = null,
    val recurringAmount: BigDecimal? = null,
    val recurringCurrency: Currency? = null,
    val parentId: Long? = null,
    val hasChildren: Boolean? = null,
    val groupId: Long? = null,
    val isGroup: Boolean,
    val assetId: Long? = null,
    val assetInstitutionName: String? = null,
    val assetName: String? = null,
    val assetDisplayName: String? = null,
    val assetStatus: LunchmoneyAssetStatus? = null,
    val plaidAccountId: Long? = null,
    val plaidAccountName: String? = null,
    val plaidAccountMask: Long? = null,
    val institutionName: String? = null,
    val plaidAccountDisplayName: String? = null,
    val plaidMetadata: String? = null,
    val plaidCategory: String? = null,
    val source: LunchmoneyTransactionSource? = null,
    val displayName: String,
    val displayNotes: String? = null,
    val accountDisplayName: String,
    val tags: List<LunchmoneyTransactionTag>? = null,
    val children: List<LunchmoneyTransactionChild>? = null,
    val externalId: String? = null,
)
