package com.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.model.InsertOrUpdateTransaction
import com.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus
import com.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import com.github.smaugfm.lunchmoney.response.InsertTransactionsResponse
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

        val insertTransactionsRequest = InsertTransactionsRequest(
            InsertTransactionRequestParams(
                listOf(
                    InsertOrUpdateTransaction(
                        LocalDate.now(),
                        BigDecimal("4.2134"),
                        12342134L,
                        "vasa",
                        Currency.getInstance("UAH"),
                        1234L,
                        123412341234L,
                        "vasa",
                        TransactionStatus.CLEARED,
                        UUID.randomUUID().toString(),
                        null
                    )
                )
            )
        )

        assertThat(api.execute(insertTransactionsRequest).block())
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
            InsertTransactionRequestParams(listOf())
        )

        assertThat { api.execute(insertTransactionsRequest).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(ApiResponseException::class)
            .prop(ApiResponseException::apiErrorResponse)
            .isNotNull()
            .prop(ApiErrorResponse::error)
            .isEqualTo(
                listOf(
                    "Transaction 0 is missing date.",
                    "Transaction 0 is missing amount.",
                    "Transaction 1 status must be either cleared or uncleared: null"
                )
            )
    }
}
