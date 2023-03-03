package com.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response

internal class ForceDeleteCategoryRequestTest : TestMockServerBase() {
    @Test
    fun forceDeleteCategoryRequestTest() {
        val id = 1234L
        mockServer
            .`when`(
                request("/categories/$id/force")
                    .withMethod("DELETE")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("true")
            )
        val forceDeleteCategoryRequest = ForceDeleteCategoryRequest(
            id
        )
        assertThat(api.execute(forceDeleteCategoryRequest).block())
            .isEqualTo(true)

    }
}