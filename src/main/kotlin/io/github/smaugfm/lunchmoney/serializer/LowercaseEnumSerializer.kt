package io.github.smaugfm.lunchmoney.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

open class LowercaseEnumSerializer<T : Enum<T>>(
    private val descriptorName: String,
    private val values: Array<T>
) : KSerializer<T> {
    override val descriptor = PrimitiveSerialDescriptor(descriptorName, PrimitiveKind.STRING)

    private val enumToStrings = values.associateWith {
        it.getSerialNameAnnotation()?.value ?: it.toString().lowercase()
    }

    private val stringsToEnum = enumToStrings.entries.associate {
        it.value to it.key
    }

    final override fun serialize(encoder: Encoder, value: T) {
        val str = enumToStrings[value]
        check(str != null) {
            "'$value' is not a valid enum $descriptorName, values are $values"
        }
        encoder.encodeString(str)
    }

    final override fun deserialize(decoder: Decoder): T {
        val str = decoder.decodeString()
        val enumValue = stringsToEnum[str]
        check(enumValue != null) {
            "'$str' is not a valid enum $descriptorName, values are $values"
        }
        return enumValue
    }

    private fun Enum<T>.getSerialNameAnnotation(): SerialName? =
        javaClass.getDeclaredField(name).getAnnotation(SerialName::class.java)
}
