package com.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.response.DeleteTransactionGroupResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

class DeleteTransactionGroupRequestTest : TestMockServerBase() {

    @Test
    fun insertTransactionsRequest() {
        val id = 123412341234L
        mockServer
            .`when`(
                request("/transactions/group/$id")
                    .withMethod("DELETE")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("{ \"transactions\": [121232, 324324, 545455] }")
            )

        val request = DeleteTransactionGroupRequest(id)

        assertThat(api.execute(request).block())
            .isEqualTo(
                DeleteTransactionGroupResponse(
                    listOf(121232, 324324, 545455)
                )
            )
    }
}
