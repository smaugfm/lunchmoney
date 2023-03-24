package io.github.smaugfm.lunchmoney.request.user

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

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
                    .withBody(getResourceAsString("response/getCurrentUser.json"))
            )
        val request = GetCurrentUserRequest()
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyUser(
                    1234L,
                    "Dummy Vasa",
                    "dummy@vasa.com",
                    12345L,
                    "My New Budget - Jan 1",
                    null
                )
            )
    }
}
