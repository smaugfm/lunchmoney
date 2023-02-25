package com.github.smaugfm.lunchmoney.response

import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.model.CategoryDeletionDependency
import kotlinx.serialization.Serializable
import java.io.InputStream

@Serializable
data class ApiErrorResponse(
    var name: String? = null,
    var message: String? = null,
    var error: String? = null,
    var dependents: CategoryDeletionDependency? = null,
) {
    fun toException(body: InputStream, statusCode: Int): ApiResponseException {
        return ApiResponseException(this, body, statusCode)
    }
}
