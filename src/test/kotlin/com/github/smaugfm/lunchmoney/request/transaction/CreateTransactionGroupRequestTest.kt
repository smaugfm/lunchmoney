package com.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams
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

        val request = CreateTransactionGroupRequest(
            CreateTransactionGroupParams(
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
