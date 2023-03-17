@file:UseSerializers(LocalDateSerializer::class, BigDecimalSerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class LunchmoneyTransactionSplit(
    val amount: BigDecimal,
    val date: LocalDate,
    val notes: String? = null,
    val categoryId: Long? = null,
    val payee: String? = null
)
