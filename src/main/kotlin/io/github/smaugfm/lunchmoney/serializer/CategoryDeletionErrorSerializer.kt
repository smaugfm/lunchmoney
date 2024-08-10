package io.github.smaugfm.lunchmoney.serializer

import io.github.smaugfm.lunchmoney.response.ApiErrorResponse.StructuredApiErrorResponse.CategoryDeletionError
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

internal object CategoryDeletionErrorSerializer :
    JsonContentPolymorphicSerializer<CategoryDeletionError>(CategoryDeletionError::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<CategoryDeletionError> {
        val dependents = element.jsonObject["dependents"]!!
        return when (dependents) {
            is JsonObject -> {
                CategoryDeletionError.CategoryDeletionDependencySingle.serializer()
            }

            is JsonArray -> {
                CategoryDeletionError.CategoryDeletionDependencyMultiple.serializer()
            }

            else -> throw SerializationException("Could not select polymorphic class")
        }
    }
}
