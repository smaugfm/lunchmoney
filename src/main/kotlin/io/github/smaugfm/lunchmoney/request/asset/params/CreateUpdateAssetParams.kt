@file:UseSerializers(
    AssetTypeSerializer::class,
    BigDecimalSerializer::class,
    InstantSerializer::class,
    LocalDateSerializer::class,
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.request.asset.params

import io.github.smaugfm.lunchmoney.model.enumeration.AssetType
import io.github.smaugfm.lunchmoney.serializer.AssetTypeSerializer
import io.github.smaugfm.lunchmoney.serializer.BigDecimalSerializer
import io.github.smaugfm.lunchmoney.serializer.CurrencySerializer
import io.github.smaugfm.lunchmoney.serializer.InstantSerializer
import io.github.smaugfm.lunchmoney.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

@Serializable
data class CreateUpdateAssetParams(
    val typeName: AssetType,
    val subtypeName: String? = null,
    val name: String,
    val displayName: String? = null,
    val balance: BigDecimal,
    val balanceAsOf: Instant? = null,
    val currency: Currency? = null,
    val institutionName: String? = null,
    val closedOn: LocalDate? = null,
    val excludeTransactions: Boolean? = null
)
