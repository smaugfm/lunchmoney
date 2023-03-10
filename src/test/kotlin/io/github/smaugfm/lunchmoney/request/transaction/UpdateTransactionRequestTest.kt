package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.InsertOrUpdateTransaction
import io.github.smaugfm.lunchmoney.model.Split
import io.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.UpdateTransactionParams
import io.github.smaugfm.lunchmoney.response.UpdateTransactionResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency
import java.util.UUID

class UpdateTransactionRequestTest : TestMockServerBase() {

    @Test
    fun updateTransactionTest() {
        val id = 12341234L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withMethod("PUT")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/updateTransaction.json"))
            )

        val request = UpdateTransactionRequest(
            id,
            UpdateTransactionParams(
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
                ),
                Split(
                    BigDecimal("1234.1234"),
                    LocalDate.now(),
                    null,
                    null,
                    null
                ),
                true,
                null
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                UpdateTransactionResponse(
                    true,
                    listOf(58, 59)
                )
            )
    }
}
