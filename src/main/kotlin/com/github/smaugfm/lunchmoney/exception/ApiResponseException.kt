package com.github.smaugfm.lunchmoney.exception

import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import java.io.InputStream

class ApiResponseException : ApiException {
    private val apiErrorResponse: ApiErrorResponse?
    private val statusCode: Int
    private val body: ByteArray

    constructor(body: InputStream, cause: Throwable?, statusCode: Int) : super(cause!!) {
        apiErrorResponse = null
        this.body = body.readBytes()
        this.statusCode = statusCode
    }

    constructor(
        apiErrorResponse: ApiErrorResponse?,
        body: InputStream,
        statusCode: Int
    ) : super(apiErrorResponse?.message ?: "Received erroneous response from Lunchmoney API") {
        this.apiErrorResponse = apiErrorResponse
        this.body = body.readBytes()
        this.statusCode = statusCode
    }
}
