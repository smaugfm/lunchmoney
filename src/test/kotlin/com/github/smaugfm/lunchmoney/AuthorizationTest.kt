package com.github.smaugfm.lunchmoney

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import org.junit.jupiter.api.Test

internal class AuthorizationTest : TestMockServerBase() {

    @Test
    fun whenNoAuthorizationHeader_ApiExceptionIsRaised() {
        val api = LunchmoneyTest(
            "invalid",
            BASE_URL,
            PORT
        )
        val request = GetCurrentUserRequest()
        assertThat { api.execute(request).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(ApiResponseException::class)
            .prop(ApiResponseException::apiErrorResponse)
            .isNotNull()
            .isEqualTo(
                ApiErrorResponse(
                    "Error",
                    "Access token does not exist.",
                    null,
                    null
                )
            )
    }
}
