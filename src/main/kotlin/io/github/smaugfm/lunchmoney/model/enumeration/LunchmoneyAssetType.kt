package io.github.smaugfm.lunchmoney.model.enumeration

import kotlinx.serialization.SerialName

enum class LunchmoneyAssetType {
    @SerialName("employee compensation")
    EMPLOYEE_COMPENSATION,
    CASH,
    VEHICLE,
    LOAN,
    CRYPTOCURRENCY,
    INVESTMENT,
    CREDIT,

    @SerialName("real estate")
    REAL_ESTATE,

    @SerialName("other asset")
    OTHER_ASSET,

    @SerialName("other liability")
    OTHER_LIABILITY
}
