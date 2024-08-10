package io.github.smaugfm.lunchmoney.api

import assertk.all
import assertk.assertFailure
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
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
        assertFailure { api.execute(request).block() }
            .isInstanceOf(RuntimeException::class)
            .prop(Throwable::cause)
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class.java)
            .all {
                prop(LunchmoneyApiResponseException::message)
                    .isEqualTo("Unknown empty response from Lunchmoney")
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
                    .withContentType(MediaType.TEXT_HTML_UTF_8)
                    .withStatusCode(500)
                    .withBody(body)
            )
        assertFailure { api.execute(GetCurrentUserRequest()).block() }
            .isInstanceOf(RuntimeException::class)
            .prop(Throwable::cause)
            .transform { it as LunchmoneyApiResponseException }
            .all {
                prop(LunchmoneyApiResponseException::message)
                    .isNotNull()
                    .isEqualTo("Unknown Lunchmoney API error. HTTP status: 500, body: \n$body")
            }
    }
}
