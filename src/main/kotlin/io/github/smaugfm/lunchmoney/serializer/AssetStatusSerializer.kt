package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetStatus

internal object AssetStatusSerializer : LowercaseEnumSerializer<LunchmoneyAssetStatus>(
    "AssetStatus",
    LunchmoneyAssetStatus.entries.toTypedArray()
)
