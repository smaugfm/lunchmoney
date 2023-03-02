package com.github.smaugfm.lunchmoney.exception

import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import java.io.InputStream

class ApiResponseException : ApiException {
    val apiErrorResponse: ApiErrorResponse?
    val statusCode: Int
    val body: String

    constructor(body: String, cause: Throwable?, statusCode: Int) : super(cause!!) {
        apiErrorResponse = null
        this.body = body
        this.statusCode = statusCode
    }

    constructor(
        apiErrorResponse: ApiErrorResponse?,
        body: String,
        statusCode: Int
    ) : super(apiErrorResponse?.message ?: "Received erroneous response from Lunchmoney API") {
        this.apiErrorResponse = apiErrorResponse
        this.body = body
        this.statusCode = statusCode
    }
}
