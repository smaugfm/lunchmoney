@file:UseSerializers(TransactionStatusSerializer::class, LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import io.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class LunchmoneyGetAllTransactionsParams(
    val tagId: Long? = null,
    val recurringId: Long? = null,
    val plaidAccountId: Long? = null,
    val categoryId: Long? = null,
    val assetId: Long? = null,
    val groupId: Long? = null,
    val isGroup: Boolean? = null,
    val status: LunchmoneyTransactionStatus? = null,
    val offset: Long? = null,
    val limit: Long? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val debitAsNegative: Boolean? = null,
    val pending: Boolean? = null
)
