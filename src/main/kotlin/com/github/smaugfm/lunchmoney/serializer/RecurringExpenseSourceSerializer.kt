package com.github.smaugfm.lunchmoney.serializer

import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseSource

object RecurringExpenseSourceSerializer :
    LowercaseEnumSerializer<RecurringExpenseSource>(
        "RecurringExpenseSource",
        RecurringExpenseSource.values()
    )
