package com.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Transaction
import com.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus
import com.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams
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
        val getSingleTransactionRequest = GetSingleTransactionRequest(id)
        assertThat(api.execute(getSingleTransactionRequest).block())
            .isEqualTo(
                Transaction(
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
                    status = TransactionStatus.CLEARED,
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
        val getSingleTransactionRequest = GetSingleTransactionRequest(
            id,
            GetSingleTransactionParams(false)
        )
        assertThat(api.execute(getSingleTransactionRequest).block())
            .isEqualTo(
                Transaction(
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
                    status = TransactionStatus.CLEARED,
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
