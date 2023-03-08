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

class UpdateAssetRequestTest : TestMockServerBase() {
    @Test
    fun updateAssetTest() {
        val id = 12L
        mockServer.`when`(
            request("/assets/$id")
                .withMethod("PUT")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/updateAsset.json"))
        )

        val request = UpdateAssetRequest(
            id,
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
                    id = 12,
                    typeName = AssetType.CASH,
                    subtypeName = "savings",
                    name = "TD Savings Account",
                    displayName = null,
                    balance = BigDecimal("28658.5300"),
                    balanceAsOf = Instant.parse("2020-03-10T05:17:23.856Z"),
                    closedOn = null,
                    currency = Currency.getInstance("CAD"),
                    institutionName = "TD Bank",
                    excludeTransactions = null,
                    createdAt = Instant.parse("2019-08-10T22:46:19.486Z")
                )
            )
    }
}
