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
    val categoryId: Long?,
    val payee: String?,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency?,
    val assetId: Long?,
    val recurringId: Long?,
    val notes: String?,
    @Serializable(with = TransactionStatusSerializer::class)
    val status: TransactionStatus?,
    val externalId: String?,
    val tags: List<Tag>?
) {

    class InsertableTransactionBuilder(
        var date: LocalDate,
        var amount: BigDecimal
    ) {
        var categoryId: Long? = null
        var payee: String? = null
        var currency: Currency? = null
        var assetId: Long? = null
        var recurringId: Long? = null
        var notes: String? = null
        var status: TransactionStatus? = null
        var externalId: String? = null
        var tags: List<Tag>? = null

        fun build(): InsertableTransaction =
            InsertableTransaction(
                date,
                amount,
                categoryId,
                payee,
                currency,
                assetId,
                recurringId,
                notes,
                status,
                externalId,
                tags
            )
    }

    companion object {
        fun builder(
            date: LocalDate,
            amount: BigDecimal,
            init: (InsertableTransactionBuilder.() -> Unit)?
        ) = InsertableTransactionBuilder(date, amount)
            .also { builder -> init?.let { builder.it() } }
            .build()
    }
}
