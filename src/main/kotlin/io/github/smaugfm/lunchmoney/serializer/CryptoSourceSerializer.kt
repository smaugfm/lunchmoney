package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyCryptoSource

internal class CryptoSourceSerializer : LowercaseEnumSerializer<LunchmoneyCryptoSource>(
    "CryptoSource",
    LunchmoneyCryptoSource.entries.toTypedArray()
)
