package io.github.smaugfm.lunchmoney.request.plaid

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyPlaidAccount
import io.github.smaugfm.lunchmoney.response.GetAllPlaidAccountsResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

class GetAllPlaidAccountsRequestTest : TestMockServerBase() {
    @Test
    fun getAllPlaidAccountsTest() {
        mockServer.`when`(
            request("/plaid_accounts")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/getAllPlaidAccounts.json"))
        )

        val request = GetAllPlaidAccountsRequest()

        assertThat(api.execute(request).block())
            .isEqualTo(
                GetAllPlaidAccountsResponse(
                    listOf(
                        LunchmoneyPlaidAccount(
                            91,
                            Instant.parse("2020-01-28T14:15:09.111Z"),
                            "401k",
                            "brokerage",
                            "401k",
                            "7468",
                            "Vanguard",
                            "inactive",
                            Instant.parse("2019-09-04T12:57:09.190Z"),
                            BigDecimal("12345.6700"),
                            Currency.getInstance("USD"),
                            Instant.parse("2020-01-27T01:38:11.862Z")
                        ),
                        LunchmoneyPlaidAccount(
                            89,
                            Instant.parse("2020-01-28T14:15:09.111Z"),
                            "Freedom",
                            "credit",
                            "credit card",
                            "1973",
                            "Chase",
                            "active",
                            Instant.parse("2019-09-04T12:57:03.250Z"),
                            BigDecimal("0.0000"),
                            Currency.getInstance("USD"),
                            Instant.parse("2020-01-27T01:38:07.460Z"),
                            15000
                        )
                    )
                )
            )
    }
}
