package com.github.smaugfm.lunchmoney.request.crypto

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Crypto
import com.github.smaugfm.lunchmoney.model.enumeration.CryptoSource
import com.github.smaugfm.lunchmoney.request.crypto.params.UpdateManualCryptoParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import java.math.BigDecimal
import java.time.Instant

class UpdateManualCryptoAssetTest : TestMockServerBase() {
    @Test
    fun updateManualCryptoAssetTest() {
        val id = 1L
        mockServer.`when`(
            request("/crypto/manual/$id").withMethod("PUT")
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
        ).respond(
            response().withStatusCode(200)
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/updateManualCryptoAsset.json"))
        )
        val request = UpdateManualCryptoAsset(
            id,
            UpdateManualCryptoParams(
                "vasa",
                "vasa",
                BigDecimal("12341234.1234123412342"),
                "vasaCoin"
            )
        )
        assertThat(api.execute(request).block())
            .isEqualTo(
                Crypto(
                    id = 1,
                    source = CryptoSource.MANUAL,
                    createdAt = Instant.parse("2021-02-10T05:57:34.305Z"),
                    name = "Shiba Token",
                    displayName = "SHIB",
                    balance = BigDecimal("12.000000000000000000"),
                    currency = "SHIB",
                    status = "active",
                    institutionName = null
                )
            )
    }
}
