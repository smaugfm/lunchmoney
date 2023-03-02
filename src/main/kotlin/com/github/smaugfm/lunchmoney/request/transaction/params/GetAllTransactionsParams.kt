package com.github.smaugfm.lunchmoney.request.transaction.params

import com.github.smaugfm.lunchmoney.model.TransactionStatus
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import com.github.smaugfm.lunchmoney.serializer.TransactionStatusSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class GetAllTransactionsParams(
    val tagId: Long? = null,
    val recurringId: Long? = null,
    val plaidAccountId: Long? = null,
    val categoryId: Long? = null,
    val assetId: Long? = null,
    val groupId: Long? = null,
    val isGroup: Boolean? = null,
    @Serializable(with = TransactionStatusSerializer::class)
    val status: TransactionStatus? = null,
    val offset: Long? = null,
    val limit: Long? = null,
    @Serializable(with = LocalDateSerializer::class)
    val startDate: LocalDate? = null,
    @Serializable(with = LocalDateSerializer::class)
    val endDate: LocalDate? = null,
    val debitAsNegative: Boolean? = null,
)
