package com.github.smaugfm.lunchmoney

import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

internal class AuthorizationTest : TestMockServerBase() {

    @Test
    fun whenNoAuthorizationHeader_ApiExceptionIsRaised() {
        val api = LunchmoneyTest(
            "invalid",
            BASE_URL,
            PORT
        )
        val getUserRequest = GetCurrentUserRequest()
        StepVerifier
            .create(api.execute(getUserRequest))
            .expectErrorMatches { e: Throwable ->
                if (e is ApiResponseException) {
                    e.apiErrorResponse!! == ApiErrorResponse(
                        "Error", "Access token does not exist.", null, null
                    )
                } else
                    false
            }
            .verify()
    }
}
