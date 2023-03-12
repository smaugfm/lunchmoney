package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseSource

internal object RecurringExpenseSourceSerializer :
    LowercaseEnumSerializer<RecurringExpenseSource>(
        "RecurringExpenseSource",
        RecurringExpenseSource.values()
    )
