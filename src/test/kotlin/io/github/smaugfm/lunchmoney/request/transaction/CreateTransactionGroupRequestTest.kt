package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.request.transaction.params.LunchmoneyCreateTransactionGroupParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.time.LocalDate

class CreateTransactionGroupRequestTest : TestMockServerBase() {
    @Test
    fun createTransactionGroupTest() {
        mockServer
            .`when`(
                request("/transactions/group")
                    .withMethod("POST")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("84389")
            )

        val request = LunchmoneyCreateTransactionGroupRequest(
            LunchmoneyCreateTransactionGroupParams(
                LocalDate.now(),
                "vasa",
                listOf(1234L, 1234L, 12343L)
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                84389L
            )
    }
}
