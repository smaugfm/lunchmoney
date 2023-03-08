package com.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
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
                    .withBody(getResourceAsString("response/createCategory.json"))
            )
        val request = CreateCategoryRequest(
            CreateUpdateCategoryRequestParams("vasa")
        )
        assertThat(api.execute(request).block())
            .isEqualTo(CreateCategoryResponse(1234L))
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
                .withBody(getResourceAsString("response/createCategory-errorAlreadyExists.json"))
        )
        val createCategoryRequest = CreateCategoryRequest(
            CreateUpdateCategoryRequestParams("vasa")
        )
        assertThat { api.execute(createCategoryRequest).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(ApiResponseException::class)
            .prop(ApiResponseException::apiErrorResponse)
            .isNotNull()
            .prop(ApiErrorResponse::error)
            .isEqualTo(listOf("A category with the same name (vasa) already exists."))
    }
}
