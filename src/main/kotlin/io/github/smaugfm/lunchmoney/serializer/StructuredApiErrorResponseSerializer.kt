package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.response.ApiErrorResponse.StructuredApiErrorResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

internal object StructuredApiErrorResponseSerializer :
    JsonContentPolymorphicSerializer<StructuredApiErrorResponse>(
        StructuredApiErrorResponse::class
    ) {
    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<StructuredApiErrorResponse> {
        val obj = element.jsonObject
        if (obj.containsKey("name")) {
            return StructuredApiErrorResponse.AccessTokenError.serializer()
        }
        if (obj.containsKey("dependents")) {
            return StructuredApiErrorResponse.CategoryDeletionError.serializer()
        }
        if (obj.containsKey("error")) {
            val error = obj["error"]!!
            when {
                error is JsonPrimitive && error.isString -> {
                    return StructuredApiErrorResponse.SingleError.serializer()
                }

                error is JsonArray -> {
                    return StructuredApiErrorResponse.MultipleErrors.serializer()
                }
            }
        }
        throw SerializationException("Could not select polymorphic class")
    }
}
