package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetType

internal object AssetTypeSerializer : LowercaseEnumSerializer<LunchmoneyAssetType>(
    "AssetType",
    LunchmoneyAssetType.values()
)
