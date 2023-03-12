package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseType

internal object RecurringExpenseTypeSerializer :
    LowercaseEnumSerializer<RecurringExpenseType>(
        "RecurringExpenseType",
        RecurringExpenseType.values()
    )
