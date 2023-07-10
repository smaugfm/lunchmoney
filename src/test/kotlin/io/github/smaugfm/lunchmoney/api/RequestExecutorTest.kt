package io.github.smaugfm.lunchmoney.api

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

class RequestExecutorTest : TestMockServerBase() {
    @Test
    fun unexpectedResponseTest() {
        val body = """
                        <!DOCTYPE html>
                        <html>
                        </html>
        """.trimIndent()
        mockServer
            .`when`(
                request("/me")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.TEXT_HTML_UTF_8)
                    .withStatusCode(500)
                    .withBody(body)
            )
        assertThat(
            assertThrows<Throwable> {
                api.execute(GetCurrentUserRequest()).block()
            }
        )
            .prop(Throwable::cause)
            .transform { it as LunchmoneyApiResponseException }
            .all {
                prop(LunchmoneyApiResponseException::statusCode)
                    .isEqualTo(500)
                prop(LunchmoneyApiResponseException::body)
                    .isEqualTo(body)
                prop(LunchmoneyApiResponseException::apiErrorResponse)
                    .isNull()
            }
    }
}
