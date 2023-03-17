package io.github.smaugfm.lunchmoney

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.user.LunchmoneyGetCurrentUserRequest
import io.github.smaugfm.lunchmoney.response.LunchmoneyApiErrorResponse
import org.junit.jupiter.api.Test

internal class AuthorizationTest : TestMockServerBase() {

    @Test
    fun whenNoAuthorizationHeader_ApiExceptionIsRaised() {
        val api = LunchmoneyTest(
            "invalid",
            BASE_URL,
            PORT
        )
        val request = LunchmoneyGetCurrentUserRequest()
        assertThat { api.execute(request).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::apiErrorResponse)
            .isNotNull()
            .isEqualTo(
                LunchmoneyApiErrorResponse(
                    "Error",
                    "Access token does not exist.",
                    null,
                    null
                )
            )
    }
}
