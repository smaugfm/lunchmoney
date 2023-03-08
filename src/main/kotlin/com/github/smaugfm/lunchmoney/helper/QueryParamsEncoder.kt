package com.github.smaugfm.lunchmoney.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.internal.NamedValueEncoder
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.modules.EmptySerializersModule

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
class QueryParamsEncoder : NamedValueEncoder() {
    private val sb = StringBuilder()
    private var empty = true

    override val serializersModule = EmptySerializersModule()
    override fun encodeTaggedValue(tag: String, value: Any) {
        val snakeCaseTag =
            JsonNamingStrategy.Builtins.SnakeCase.serialNameForJson(
                NothingSerialDescriptor,
                0,
                tag
            )
        if (empty) {
            sb.append("?")
            empty = false
        } else {
            sb.append("&")
        }
        sb.append("$snakeCaseTag=$value")
    }

    override fun encodeTaggedNull(tag: String) {
        // ignore
    }

    companion object {
        fun <T> encode(serializer: SerializationStrategy<T>, value: T) =
            QueryParamsEncoder()
                .also {
                    it.encodeSerializableValue(serializer, value)
                }.sb.toString()

        private object NothingSerialDescriptor : SerialDescriptor {
            override val kind: SerialKind = StructureKind.OBJECT

            override val serialName: String = "kotlin.Nothing"

            override val elementsCount: Int get() = 0
            override fun getElementName(index: Int): String = error()
            override fun getElementIndex(name: String): Int = error()
            override fun isElementOptional(index: Int): Boolean = error()
            override fun getElementDescriptor(index: Int): SerialDescriptor = error()
            override fun getElementAnnotations(index: Int): List<Annotation> = error()
            override fun toString(): String = "NothingSerialDescriptor"
            override fun equals(other: Any?): Boolean {
                return this === other
            }

            override fun hashCode(): Int = serialName.hashCode() + 31 * kind.hashCode()
            private fun error(): Nothing =
                error("Descriptor for type `kotlin.Nothing` does not have elements")
        }
    }
}
