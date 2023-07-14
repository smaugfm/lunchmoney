package io.github.smaugfm.lunchmoney.exception

import io.github.smaugfm.lunchmoney.response.ApiErrorResponse
import io.netty.handler.codec.http.HttpResponseStatus

@Suppress("MemberVisibilityCanBePrivate")
class LunchmoneyApiResponseException : LunchmoneyApiException {
    val apiErrorResponse: ApiErrorResponse?
    val statusCode: Int
    val body: String

    constructor(
        body: String,
        cause: ApiErrorResponse,
        statusCode: Int
    ) : super(errorMessage(cause)) {
        this.body = body
        this.apiErrorResponse = cause
        this.statusCode = statusCode
    }

    constructor(
        body: String,
        cause: Throwable,
        statusCode: Int
    ) : super(cause) {
        this.body = body
        this.apiErrorResponse = null
        this.statusCode = statusCode
    }

    constructor(
        statusCode: Int
    ) : super(
        "Response body is empty but status is $statusCode " +
            HttpResponseStatus.valueOf(statusCode).reasonPhrase()
    ) {
        this.body = ""
        this.apiErrorResponse = null
        this.statusCode = statusCode
    }

    companion object {
        private fun errorMessage(apiErrorResponse: ApiErrorResponse): String {
            val msg = apiErrorResponse.message
            if (msg != null) {
                return msg
            }

            return apiErrorResponse.error?.joinToString(", ")
                ?: "Received erroneous response from Lunchmoney API"
        }
    }
}
