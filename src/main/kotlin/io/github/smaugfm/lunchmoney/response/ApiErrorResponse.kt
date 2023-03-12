package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.exception.ApiResponseException
import io.github.smaugfm.lunchmoney.model.CategoryDeletionDependency
import io.github.smaugfm.lunchmoney.serializer.StringOrStringArrayDeserializer
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    var name: String? = null,
    var message: String? = null,
    @Serializable(with = StringOrStringArrayDeserializer::class)
    var error: List<String>? = null,
    var dependents: CategoryDeletionDependency? = null
) {
    fun toException(body: String, statusCode: Int): ApiResponseException {
        return ApiResponseException(this, body, statusCode)
    }
}
