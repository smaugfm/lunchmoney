package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.CryptoSource

internal class CryptoSourceSerializer : LowercaseEnumSerializer<CryptoSource>(
    "CryptoSource",
    CryptoSource.values()
)
