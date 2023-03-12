package io.github.smaugfm.lunchmoney.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.serializer

@OptIn(ExperimentalSerializationApi::class)
open class PathAndQuery protected constructor(
    private val json: Json = Json {
        namingStrategy = JsonNamingStrategy.SnakeCase
    }
) {
    private val segments = StringBuilder()
    private var query = String()

    fun segment(folder: Any): PathAndQuery {
        val str: String = checkBlank(folder)
        segments.append("/")
        segments.append(str)
        return this
    }

    inline fun <reified T> query(value: T?): PathAndQuery {
        query(Json.serializersModule.serializer(), value)
        return this
    }

    fun <T> query(serializer: KSerializer<T>, value: T?): PathAndQuery {
        if (value != null) {
            query = QueryParamsEncoder.encode(serializer, value)
        }
        return this
    }

    override fun toString() =
        segments.append(query).toString()

    companion object {
        fun segment(folder: Any) =
            PathAndQuery().segment(folder)

        inline fun <reified T> query(value: T?) =
            query(Json.serializersModule.serializer(), value)

        fun <T> query(serializer: KSerializer<T>, value: T?) =
            PathAndQuery().query(serializer, value)

        private fun checkBlank(folder: Any) =
            folder.toString().also {
                require(it.isNotBlank()) { "folder is blank" }
            }
    }
}
