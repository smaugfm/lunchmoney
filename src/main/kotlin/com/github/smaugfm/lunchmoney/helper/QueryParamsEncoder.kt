package com.github.smaugfm.lunchmoney.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule

@OptIn(ExperimentalSerializationApi::class)
class QueryParamsEncoder : AbstractEncoder() {
    private val sb = StringBuilder()
    private var empty = true

    override val serializersModule = EmptySerializersModule()
    override fun encodeValue(value: Any) {
        if (empty) {
            sb.append("?")
            empty = false
        } else {
            sb.append("&")
        }
        sb.append(value)
    }

    companion object {
        fun <T> encode(serializer: SerializationStrategy<T>, value: T) =
            QueryParamsEncoder()
                .also {
                    it.encodeSerializableValue(serializer, value)
                }.sb.toString()
    }
}
