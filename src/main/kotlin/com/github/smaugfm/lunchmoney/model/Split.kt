@file:UseSerializers(LocalDateSerializer::class, BigDecimalSerializer::class)

package com.github.smaugfm.lunchmoney.model

import com.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import com.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class Split(
    val amount: BigDecimal,
    val date: LocalDate,
    val notes: String? = null,
    val categoryId: Long? = null,
    val payee: String? = null
)
