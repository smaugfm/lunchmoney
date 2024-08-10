package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionSource

class TransactionSourceSerializer : LowercaseEnumSerializer<LunchmoneyTransactionSource>(
    "TransactionSource",
    LunchmoneyTransactionSource.entries.toTypedArray()
)
