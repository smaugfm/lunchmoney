package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseType

object RecurringExpenseTypeSerializer :
    LowercaseEnumSerializer<RecurringExpenseType>(
        "RecurringExpenseType",
        RecurringExpenseType.values()
    )
