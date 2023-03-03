package com.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response

internal class UpdateCategoryRequestTest : TestMockServerBase() {
    @Test
    fun updateCategoryRequestTest() {
        val id = 1234L
        mockServer.`when`(
                request("/categories/$id").withMethod("PUT")
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
            ).respond(
                response().withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("true")
            )
        val createCategoryRequest = UpdateCategoryRequest(
            id, CreateUpdateCategoryRequestParams("vasa")
        )
        assertThat(api.execute(createCategoryRequest).block()).isEqualTo(true)

    }
}
