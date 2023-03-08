package com.github.smaugfm.lunchmoney.request.asset

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Asset
import com.github.smaugfm.lunchmoney.model.enumeration.AssetType
import com.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

class CreateAssetRequestTest : TestMockServerBase() {
    @Test
    fun createAssetTest() {
        mockServer.`when`(
            request("/assets")
                .withMethod("POST")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("createAsset.json"))
        )

        val request = CreateAssetRequest(
            CreateUpdateAssetParams(
                typeName = AssetType.CASH,
                subtypeName = "vasa",
                name = "vasa",
                balance = BigDecimal.ZERO,
                displayName = "vasa",
                balanceAsOf = Instant.now(),
                currency = Currency.getInstance("USD"),
                institutionName = "vasa",
                closedOn = LocalDate.now(),
                excludeTransactions = false
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                Asset(
                    id = 34061,
                    typeName = AssetType.CASH,
                    subtypeName = null,
                    name = "My Test Asset",
                    displayName = null,
                    balance = BigDecimal("67.2100"),
                    balanceAsOf = Instant.parse("2022-05-29T21:35:36.557Z"),
                    closedOn = null,
                    currency = Currency.getInstance("CAD"),
                    institutionName = null,
                    excludeTransactions = false,
                    createdAt = Instant.parse("2022-05-29T21:35:36.564Z"),
                )
            )
    }
}
