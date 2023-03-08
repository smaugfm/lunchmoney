package com.github.smaugfm.lunchmoney.request.asset

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Asset
import com.github.smaugfm.lunchmoney.model.enumeration.AssetType
import com.github.smaugfm.lunchmoney.response.GetAllAssetsResponse
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
        val request = GetAllAssetsRequest()
        assertThat(api.execute(request).block())
            .isEqualTo(
                GetAllAssetsResponse(
                    listOf(
                        Asset(
                            47653,
                            AssetType.INVESTMENT,
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
