package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.CryptoSource

internal class CryptoSourceSerializer : LowercaseEnumSerializer<CryptoSource>(
    "CryptoSource",
    CryptoSource.values()
)
