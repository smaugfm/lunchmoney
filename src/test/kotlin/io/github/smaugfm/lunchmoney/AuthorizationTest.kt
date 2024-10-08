package io.github.smaugfm.lunchmoney

import assertk.assertFailure
import assertk.assertions.cause
import assertk.assertions.contains
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import org.junit.jupiter.api.Test

internal class AuthorizationTest : TestMockServerBase() {

    @Test
    fun whenInvalidAuthorizationHeader_ApiExceptionIsRaised() {
        val api = LunchmoneyTest(
            "invalid",
            BASE_URL,
            PORT
        )
        val request = GetCurrentUserRequest()
        assertFailure { api.execute(request).block() }
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .contains("Access token does not exist.")
    }
}
