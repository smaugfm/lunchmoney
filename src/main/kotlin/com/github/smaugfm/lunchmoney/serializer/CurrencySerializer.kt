package com.github.smaugfm.lunchmoney.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Currency

object CurrencySerializer : KSerializer<Currency> {
    override val descriptor =
        PrimitiveSerialDescriptor(Currency::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Currency {
        return Currency.getInstance(decoder.decodeString().uppercase())
    }

    override fun serialize(encoder: Encoder, value: Currency) {
        encoder.encodeString(value.toString().lowercase())
    }
}
