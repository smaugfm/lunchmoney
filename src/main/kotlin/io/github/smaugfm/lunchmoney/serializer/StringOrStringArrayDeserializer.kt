package io.github.smaugfm.lunchmoney.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class StringOrStringArrayDeserializer : KSerializer<List<String>> {
    private val serializer = ListSerializer(StringSerializer)
    override val descriptor = SerialDescriptor("PossibleStringArray", serializer.descriptor)

    @Suppress("SwallowedException")
    override fun deserialize(decoder: Decoder): List<String> {
        return try {
            listOf(decoder.decodeString())
        } catch (e: SerializationException) {
            decoder.decodeSerializableValue(serializer)
        }
    }

    override fun serialize(encoder: Encoder, value: List<String>) {
        throw UnsupportedOperationException()
    }

    companion object {

        object StringSerializer : KSerializer<String> {
            override val descriptor: SerialDescriptor =
                PrimitiveSerialDescriptor("custom.kotlin.String", PrimitiveKind.STRING)

            override fun serialize(encoder: Encoder, value: String): Unit =
                encoder.encodeString(value)

            override fun deserialize(decoder: Decoder): String = decoder.decodeString()
        }
    }
}
