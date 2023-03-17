@file:UseSerializers(InstantSerializer::class)

package io.github.smaugfm.lunchmoney.model

import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Instant

@Serializable
data class LunchmoneyCategoryChild(
    val id: Long,
    val name: String,
    val description: String? = null,
    val createdAt: Instant
)
