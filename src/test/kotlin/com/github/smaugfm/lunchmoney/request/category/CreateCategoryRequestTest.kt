package com.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.exception.ApiResponseException
import com.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import com.github.smaugfm.lunchmoney.response.CreateCategoryResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import reactor.test.StepVerifier

internal class CreateCategoryRequestTest : TestMockServerBase() {
    @Test
    fun createCategoryRequestTest() {
        mockServer
            .`when`(
                request("/categories")
                    .withMethod("POST")
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("createCategory.json"))
            )
        val createCategoryRequest = CreateCategoryRequest(
            CreateUpdateCategoryRequestParams("vasa")
        )
        StepVerifier
            .create(api.execute(createCategoryRequest))
            .expectNext(CreateCategoryResponse(1234L))
            .verifyComplete()
    }

    @Test
    fun createCategoryRequestErrorAlreadyExists() {
        mockServer.`when`(
                request("/categories")
                    .withMethod("POST")
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("createCategory-errorAlreadyExists.json"))
            )
        val createCategoryRequest = CreateCategoryRequest(
            CreateUpdateCategoryRequestParams("vasa")
        )
        StepVerifier
            .create(api.execute(createCategoryRequest))
            .expectErrorSatisfies { error: Throwable ->
                assertThat(error)
                    .isInstanceOf(ApiResponseException::class)
                    .prop(ApiResponseException::apiErrorResponse)
                    .isNotNull()
                    .prop(ApiErrorResponse::error)
                    .isEqualTo("A category with the same name (vasa) already exists.")
            }.verify()
    }
}
