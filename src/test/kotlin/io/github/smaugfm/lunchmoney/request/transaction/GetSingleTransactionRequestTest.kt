package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyGetSingleTransactionParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

internal class GetSingleTransactionRequestTest : TestMockServerBase() {
    @Test
    fun singleTransactionTestSimple() {
        val id = 602L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getSingleTransaction.json"))
            )
        val request = LunchmoneyGetSingleTransactionRequest(id)
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyTransaction(
                    id = 602L,
                    date = LocalDate.of(2020, 1, 1),
                    payee = "Starbucks",
                    amount = BigDecimal("4.5000"),
                    currency = Currency.getInstance("CAD"),
                    toBase = 4.5,
                    notes = "Frappuccino",
                    categoryId = null,
                    assetId = null,
                    recurringId = null,
                    plaidAccountId = null,
                    status = LunchmoneyTransactionStatus.CLEARED,
                    parentId = null,
                    isGroup = false,
                    groupId = null,
                    externalId = null,
                    tags = null,
                    originalName = "STARBUCKS NW 32804",
                    type = null,
                    subtype = null,
                    fees = null,
                    price = null,
                    quantity = null
                )
            )
    }

    @Test
    fun singleTransactionTestSimpleWithQuery() {
        val id = 602L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withQueryStringParameter("debit_as_negative", "false")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getSingleTransaction.json"))
            )
        val getSingleTransactionRequest = LunchmoneyGetSingleTransactionRequest(
            id,
            LunchmoneyGetSingleTransactionParams(false)
        )
        assertThat(api.execute(getSingleTransactionRequest).block())
            .isEqualTo(
                LunchmoneyTransaction(
                    id = 602L,
                    date = LocalDate.of(2020, 1, 1),
                    payee = "Starbucks",
                    amount = BigDecimal("4.5000"),
                    currency = Currency.getInstance("CAD"),
                    toBase = 4.5,
                    notes = "Frappuccino",
                    categoryId = null,
                    assetId = null,
                    recurringId = null,
                    plaidAccountId = null,
                    status = LunchmoneyTransactionStatus.CLEARED,
                    parentId = null,
                    isGroup = false,
                    groupId = null,
                    externalId = null,
                    tags = null,
                    originalName = "STARBUCKS NW 32804",
                    type = null,
                    subtype = null,
                    fees = null,
                    price = null,
                    quantity = null
                )
            )
    }
}
