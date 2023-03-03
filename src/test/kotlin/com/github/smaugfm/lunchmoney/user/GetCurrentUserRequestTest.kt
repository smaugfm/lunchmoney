package com.github.smaugfm.lunchmoney.user

import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.User
import com.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import reactor.test.StepVerifier

internal class GetCurrentUserRequestTest : TestMockServerBase() {
    @Test
    fun currentUserRequestTest() {
        mockServer
            .`when`(
                request("/me")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("getCurrentUser.json"))
            )
        val getUserRequest = GetCurrentUserRequest()
        StepVerifier
            .create(api.execute(getUserRequest))
            .expectNext(
                User(
                    1234L,
                    "Dummy Vasa",
                    "dummy@vasa.com",
                    12345L,
                    "My New Budget - Jan 1",
                    null
                )
            )
            .verifyComplete()
    }
}
