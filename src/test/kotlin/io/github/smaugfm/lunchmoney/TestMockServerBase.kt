package io.github.smaugfm.lunchmoney

import io.netty.handler.codec.http.HttpHeaderNames
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.mockserver.configuration.Configuration.configuration
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.mockserver.model.NottableString
import org.slf4j.event.Level

open class TestMockServerBase {
    protected val api: io.github.smaugfm.lunchmoney.api.Lunchmoney = LunchmoneyTest(
        TOKEN,
        BASE_URL,
        PORT
    )

    @BeforeEach
    fun createClient() {
        mockServer.reset()
        mockServer
            .`when`(
                HttpRequest.request()
                    .withHeader(
                        NottableString.not(HttpHeaderNames.AUTHORIZATION.toString())
                    )
            ).respond(
                HttpResponse.response()
                    .withStatusCode(200)
                    .withBody(
                        " { \"name\": \"Error\", " +
                            "\"message\": \"Access token does not exist.\" } "
                    )
            )
        mockServer
            .`when`(
                HttpRequest.request()
                    .withHeader(
                        NottableString.string(HttpHeaderNames.AUTHORIZATION.toString()),
                        NottableString.not("Bearer $TOKEN")
                    )
            ).respond(
                HttpResponse.response()
                    .withStatusCode(200)
                    .withBody(
                        " { \"name\": \"Error\", " +
                            "\"message\": \"Access token does not exist.\" } "
                    )
            )
    }

    class LunchmoneyTest(
        token: String,
        baseUrl: String,
        port: Int
    ) : io.github.smaugfm.lunchmoney.api.Lunchmoney(token, baseUrl, port)

    companion object {
        const val TOKEN = "validToken"
        const val PORT = 1080

        @JvmStatic
        protected val BASE_URL = "http://127.0.0.1:$PORT"

        @JvmStatic
        protected lateinit var mockServer: ClientAndServer

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            mockServer = ClientAndServer
                .startClientAndServer(
                    configuration()
                        .logLevel(Level.INFO),
                    PORT
                )
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            mockServer.stop()
        }
    }
}
