package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.AssetType

internal object AssetTypeSerializer : LowercaseEnumSerializer<AssetType>(
    "AssetType",
    AssetType.values()
)
