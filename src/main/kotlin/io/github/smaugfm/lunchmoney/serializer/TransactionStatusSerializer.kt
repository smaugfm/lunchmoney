package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus

internal object TransactionStatusSerializer :
    LowercaseEnumSerializer<LunchmoneyTransactionStatus>(
        "TransactionStatus",
        LunchmoneyTransactionStatus.entries.toTypedArray()
    )
