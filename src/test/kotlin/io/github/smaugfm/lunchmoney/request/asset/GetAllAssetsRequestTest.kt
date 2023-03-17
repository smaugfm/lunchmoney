package io.github.smaugfm.lunchmoney.request.asset

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetType
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetAllAssetsResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency

class GetAllAssetsRequestTest : TestMockServerBase() {

    @Test
    fun getAllAssetsTest() {
        mockServer.`when`(
            request("/assets")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/getAllAssets.json"))
        )
        val request = LunchmoneyGetAllAssetsRequest()
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyGetAllAssetsResponse(
                    listOf(
                        LunchmoneyAsset(
                            47653,
                            LunchmoneyAssetType.INVESTMENT,
                            "brokerage",
                            "Individual Brokerage",
                            null,
                            BigDecimal("41211.8000"),
                            Instant.parse("2023-02-02T14:57:53.000Z"),
                            null,
                            Currency.getInstance("USD"),
                            "Fidelity",
                            false,
                            Instant.parse("2023-02-02T14:57:53.477Z")
                        )
                    )
                )
            )
    }
}
