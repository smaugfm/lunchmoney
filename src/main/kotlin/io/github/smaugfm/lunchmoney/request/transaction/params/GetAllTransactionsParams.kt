@file:UseSerializers(TransactionStatusSerializer::class, LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import io.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
internal data class GetAllTransactionsParams(
    val tagId: Long?,
    val recurringId: Long?,
    val plaidAccountId: Long?,
    val categoryId: Long?,
    val assetId: Long?,
    val groupId: Long?,
    val isGroup: Boolean?,
    val status: LunchmoneyTransactionStatus?,
    val offset: Long?,
    val limit: Long?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val debitAsNegative: Boolean?,
    val pending: Boolean?,
)
