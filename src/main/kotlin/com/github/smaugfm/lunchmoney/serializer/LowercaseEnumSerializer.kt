package com.github.smaugfm.lunchmoney.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal open class LowercaseEnumSerializer<T : Enum<T>>(
    private val serialName: String,
    private val values: Array<T>
) : KSerializer<T> {
    override val descriptor = PrimitiveSerialDescriptor(serialName, PrimitiveKind.STRING)
    private val valueStrings = values.map { it.toString() }

    final override fun serialize(encoder: Encoder, value: T) {
        val index = values.indexOf(value)
            .also {
                check(it >= 0) {
                    "$value is not a valid enum $serialName, values are $values"
                }
            }
        encoder.encodeString(values[index].toString().lowercase())
    }

    final override fun deserialize(decoder: Decoder): T {
        val value = decoder.decodeString().uppercase()
        val index = valueStrings.indexOf(value)
            .also {
                check(it >= 0) {
                    "$value is not a valid enum $serialName, values are $values"
                }
            }
        return values[index]
    }
}
