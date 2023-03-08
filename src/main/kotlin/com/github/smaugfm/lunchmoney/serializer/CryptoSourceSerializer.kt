package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.CryptoSource

class CryptoSourceSerializer : LowercaseEnumSerializer<CryptoSource>(
    "CryptoSource",
    CryptoSource.values()
)
