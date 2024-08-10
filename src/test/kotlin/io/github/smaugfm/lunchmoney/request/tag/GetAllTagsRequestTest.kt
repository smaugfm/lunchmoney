package io.github.smaugfm.lunchmoney.request.tag

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isNotNull
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionTag
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

internal class GetAllTagsRequestTest : TestMockServerBase() {
    @Test
    fun allTagsTest() {
        mockServer
            .`when`(
                request("/tags")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getAllTags.json"))
            )
        val request = GetAllTagsRequest()
        assertThat(api.execute(request).block())
            .isNotNull()
            .containsExactlyInAnyOrder(
                LunchmoneyTransactionTag(1807L, "Wedding", "All wedding-related expenses", false),
                LunchmoneyTransactionTag(1808L, "Honeymoon", "All honeymoon-related expenses", false)
            )
    }
}
