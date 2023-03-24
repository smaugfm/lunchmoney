package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.request.transaction.params.UnsplitTransactionsParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

class UnsplitTransactionsRequestTest : TestMockServerBase() {
    @Test
    fun unsplitTransactionsTest() {
        mockServer
            .`when`(
                request("/transactions/unsplit")
                    .withMethod("POST")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("[84389, 23212, 43333]")
            )

        val request = UnsplitTransactionsRequest(
            UnsplitTransactionsParams(
                listOf(1234, 1234),
                null
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                listOf<Long>(84389, 23212, 43333)
            )
    }
}
