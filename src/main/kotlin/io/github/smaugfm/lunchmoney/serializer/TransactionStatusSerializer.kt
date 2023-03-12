package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus

internal object TransactionStatusSerializer :
    LowercaseEnumSerializer<TransactionStatus>(
        "TransactionStatus",
        TransactionStatus.values()
    )
