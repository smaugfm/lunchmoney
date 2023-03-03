package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.TestMockServerBase
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import reactor.test.StepVerifier

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
        StepVerifier
            .create(api.execute(forceDeleteCategoryRequest))
            .expectNext(true)
            .verifyComplete()
    }
}
