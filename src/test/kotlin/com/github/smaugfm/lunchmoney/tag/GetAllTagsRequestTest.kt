package com.github.smaugfm.lunchmoney.tag

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isNotNull
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Tag
import com.github.smaugfm.lunchmoney.request.tag.GetAllTagsRequest
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
                    .withBody(getResourceAsString("getAllTags.json"))
            )
        val getAllTagsRequest = GetAllTagsRequest()
        assertThat(api.execute(getAllTagsRequest).block())
            .isNotNull()
            .containsExactlyInAnyOrder(
                Tag(1807L, "Wedding", "All wedding-related expenses"),
                Tag(1808L, "Honeymoon", "All honeymoon-related expenses")
            )
    }
}