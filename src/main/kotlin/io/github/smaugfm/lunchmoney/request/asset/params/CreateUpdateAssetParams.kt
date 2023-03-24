@file:UseSerializers(
    AssetTypeSerializer::class,
    BigDecimalSerializer::class,
    InstantSerializer::class,
    LocalDateSerializer::class,
    CurrencySerializer::class
)

package io.github.smaugfm.lunchmoney.request.asset.params

import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetType
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
internal data class CreateUpdateAssetParams(
    val typeName: LunchmoneyAssetType?,
    val subtypeName: String?,
    val name: String?,
    val displayName: String?,
    val balance: BigDecimal?,
    val balanceAsOf: Instant?,
    val currency: Currency?,
    val institutionName: String?,
    val closedOn: LocalDate?,
    val excludeTransactions: Boolean?
)
