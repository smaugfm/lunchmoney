package io.github.smaugfm.lunchmoney.exception

import io.github.smaugfm.lunchmoney.response.ApiErrorResponse

@Suppress("MemberVisibilityCanBePrivate")
class LunchmoneyApiResponseException : LunchmoneyApiException {
    val apiErrorResponse: ApiErrorResponse?
    val statusCode: Int
    val body: String

    constructor(
        body: String,
        cause: Throwable?,
        statusCode: Int
    ) : super(cause!!) {
        this.apiErrorResponse = null
        this.body = body
        this.statusCode = statusCode
    }

    constructor(
        body: String,
        apiErrorResponse: ApiErrorResponse,
        statusCode: Int
    ) : super(
        errorMessage(apiErrorResponse)
    ) {
        this.apiErrorResponse = apiErrorResponse
        this.body = body
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
