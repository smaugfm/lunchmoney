package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import io.github.smaugfm.lunchmoney.response.InsertTransactionsResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency
import java.util.UUID

internal class InsertTransactionsRequestTest : TestMockServerBase() {

    @Test
    fun insertTransactionsRequest() {
        mockServer
            .`when`(
                request("/transactions")
                    .withMethod("POST")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/insertTransactions.json"))
            )

        val request = InsertTransactionsRequest(
            InsertTransactionRequestParams(
                listOf(
                    LunchmoneyInsertTransaction(
                        LocalDate.now(),
                        BigDecimal("4.2134"),
                        12342134L,
                        "vasa",
                        Currency.getInstance("UAH"),
                        1234L,
                        123412341234L,
                        "vasa",
                        LunchmoneyTransactionStatus.CLEARED,
                        UUID.randomUUID().toString(),
                        null
                    )
                ),
                null,
                null,
                null,
                null,
                null
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                InsertTransactionsResponse(
                    listOf(54L)
                )
            )
    }

    @Test
    fun insertTransactionsRequestError() {
        mockServer
            .`when`(
                request("/transactions")
                    .withMethod("POST")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/insertTransactions-error.json"))
            )

        val insertTransactionsRequest = InsertTransactionsRequest(
            InsertTransactionRequestParams(
                listOf(),
                null,
                null,
                null,
                null,
                null
            )
        )

        assertFailure { api.execute(insertTransactionsRequest).block() }
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .isEqualTo(
                listOf(
                    "Transaction 0 is missing date.",
                    "Transaction 0 is missing amount.",
                    "Transaction 1 status must be either cleared or uncleared: null"
                ).joinToString("\n")
            )
    }
}
