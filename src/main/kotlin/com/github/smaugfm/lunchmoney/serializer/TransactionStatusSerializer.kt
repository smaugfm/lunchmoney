package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus

object TransactionStatusSerializer :
    LowercaseEnumSerializer<TransactionStatus>(
        "TransactionStatus",
        TransactionStatus.values()
    )
