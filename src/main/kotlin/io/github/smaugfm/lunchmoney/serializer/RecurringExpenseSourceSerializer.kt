package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseSource

internal object RecurringExpenseSourceSerializer :
    LowercaseEnumSerializer<LunchmoneyRecurringExpenseSource>(
        "RecurringExpenseSource",
        LunchmoneyRecurringExpenseSource.entries.toTypedArray()
    )
