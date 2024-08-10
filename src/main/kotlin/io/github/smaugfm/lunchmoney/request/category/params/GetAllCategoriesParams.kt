package io.github.smaugfm.lunchmoney.request.category.params

import io.github.smaugfm.lunchmoney.serializer.LowercaseEnumSerializer
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAllCategoriesParams(
    @Serializable(with = FormatSerializer::class)
    val format: Format? = null
) {

    enum class Format {
        Flattened,
        Nested
    }

    internal object FormatSerializer : LowercaseEnumSerializer<Format>(
        "AssetType",
        Format.entries.toTypedArray()
    )
}
