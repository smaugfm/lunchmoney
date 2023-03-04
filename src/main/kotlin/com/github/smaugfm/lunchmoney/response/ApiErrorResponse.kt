package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.model.CategoryDeletionDependency
import com.github.smaugfm.lunchmoney.serializer.StringOrStringArrayDeserializer
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
