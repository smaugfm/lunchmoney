package com.github.smaugfm.lunchmoney.helper

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.serializer

@OptIn(ExperimentalSerializationApi::class)
class PathAndQuery private constructor(
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
        setQuery(Json.serializersModule.serializer(), value)
        return this
    }

    fun <T> setQuery(serializer: KSerializer<T>, value: T?) {
        if (value != null)
            query = QueryParamsEncoder.encode(serializer, value)
    }

    override fun toString() = segments.append(query).toString()

    companion object {
        fun segment(folder: Any) =
            PathAndQuery().segment(folder)

        inline fun <reified T> query(value: T?) =
            query(Json.serializersModule.serializer(), value)

        fun <T> query(serializer: KSerializer<T>, value: T?) =
            PathAndQuery().also {
                it.setQuery(serializer, value)
            }

        private fun checkBlank(folder: Any) =
            folder.toString().also {
                require(it.isNotBlank()) { "folder is blank" }
            }
    }
}
