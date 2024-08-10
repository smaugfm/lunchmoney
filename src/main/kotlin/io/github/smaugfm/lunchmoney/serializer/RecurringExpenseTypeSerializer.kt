package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseType

internal object RecurringExpenseTypeSerializer :
    LowercaseEnumSerializer<LunchmoneyRecurringExpenseType>(
        "RecurringExpenseType",
        LunchmoneyRecurringExpenseType.entries.toTypedArray()
    )
