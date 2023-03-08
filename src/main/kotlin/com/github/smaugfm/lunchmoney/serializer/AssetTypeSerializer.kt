package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.AssetType

object AssetTypeSerializer : LowercaseEnumSerializer<AssetType>(
    "AssetType",
    AssetType.values()
)
