package io.github.smaugfm.lunchmoney.exception

import io.github.smaugfm.lunchmoney.response.LunchmoneyApiErrorResponse

@Suppress("MemberVisibilityCanBePrivate")
class LunchmoneyApiResponseException : LunchmoneyApiException {
    val apiErrorResponse: LunchmoneyApiErrorResponse?
    val statusCode: Int
    val body: String

    constructor(body: String, cause: Throwable?, statusCode: Int) : super(cause!!) {
        apiErrorResponse = null
        this.body = body
        this.statusCode = statusCode
    }

    constructor(
        apiErrorResponse: LunchmoneyApiErrorResponse?,
        body: String,
        statusCode: Int
    ) : super(apiErrorResponse?.message ?: "Received erroneous response from Lunchmoney API") {
        this.apiErrorResponse = apiErrorResponse
        this.body = body
        this.statusCode = statusCode
    }
}
