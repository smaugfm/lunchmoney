@file:UseSerializers(LocalDateSerializer::class)

package io.github.smaugfm.lunchmoney.request.transaction.params

import io.github.smaugfm.lunchmoney.model.Tag
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class CreateTransactionGroupParams(
    val date: LocalDate,
    val payee: String,
    val transactions: List<Long>,
    val categoryId: Long? = null,
    val notes: String? = null,
    val tags: List<Tag>? = null
)
