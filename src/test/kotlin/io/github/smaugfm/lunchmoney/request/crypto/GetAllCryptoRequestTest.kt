package io.github.smaugfm.lunchmoney.request.crypto

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyCryptoSource
import io.github.smaugfm.lunchmoney.response.GetAllCryptoResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant

class GetAllCryptoRequestTest : TestMockServerBase() {

    @Test
    fun getAllCryptoTest() {
        mockServer.`when`(
            request("/crypto")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/getAllCrypto.json"))
        )

        val request = GetAllCryptoRequest()
        assertThat(api.execute(request).block())
            .isEqualTo(
                GetAllCryptoResponse(
                    listOf(
                        LunchmoneyCrypto(
                            id = null,
                            zaboAccountId = 544,
                            source = LunchmoneyCryptoSource.SYNCED,
                            name = "Dogecoin",
                            displayName = null,
                            balance = BigDecimal("1.902383849000000000"),
                            balanceAsOf = Instant.parse("2021-05-21T00:05:36.000Z"),
                            currency = "doge",
                            status = "active",
                            institutionName = "MetaMask",
                            createdAt = Instant.parse("2020-07-27T11:53:02.722Z")
                        ),
                        LunchmoneyCrypto(
                            id = 152,
                            zaboAccountId = null,
                            source = LunchmoneyCryptoSource.MANUAL,
                            name = "Ether",
                            displayName = "BlockFi - ETH",
                            balance = BigDecimal("5.391445130000000000"),
                            balanceAsOf = Instant.parse("2021-05-20T16:57:00.000Z"),
                            currency = "ETH",
                            status = "active",
                            institutionName = "BlockFi",
                            createdAt = Instant.parse("2021-04-03T04:16:48.230Z")
                        )
                    )
                )
            )
    }
}
