package io.github.smaugfm.lunchmoney.api

import assertk.all
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.github.resilience4j.core.IntervalFunction
import io.github.resilience4j.kotlin.retry.RetryConfig
import io.github.resilience4j.reactor.retry.RetryOperator
import io.github.resilience4j.retry.Retry
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import org.mockserver.verify.VerificationTimes
import java.time.Duration

class RequestExecutorTest : TestMockServerBase() {
    @Test
    fun retryTest() {
        val retry = Retry.of(
            "vasa",
            RetryConfig {
                maxAttempts(3)
                failAfterMaxAttempts(true)
                intervalFunction(IntervalFunction.of(Duration.ofMillis(1)))
            }
        )
        val api: LunchmoneyApiInternal = LunchmoneyTest(
            TOKEN,
            BASE_URL,
            PORT,
            null,
            RetryOperator.of(retry)
        )

        mockServer
            .`when`(
                request("/me")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(500)
            )
        val request = GetCurrentUserRequest()
        assertThat { api.execute(request).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .prop(Throwable::cause)
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class.java)
            .all {
                prop(LunchmoneyApiResponseException::apiErrorResponse)
                    .isNull()
                prop(LunchmoneyApiResponseException::body)
                    .isEmpty()
                prop(LunchmoneyApiResponseException::statusCode)
                    .isEqualTo(500)
            }
        mockServer.verify(
            request("/me")
                .withMethod("GET"),
            VerificationTimes.exactly(3)
        )
    }

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
        assertThat { api.execute(GetCurrentUserRequest()).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
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
