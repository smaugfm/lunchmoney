package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.response.CreateCategoryResponse
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
            CreateUpdateCategoryRequestParams(
                "vasa",
                false,
                false,
                false,
                null,
                null,
                null
            )
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
            CreateUpdateCategoryRequestParams(
                "vasa",
                false,
                false,
                false,
                null,
                null,
                null
            )
        )
        assertThat { api.execute(createCategoryRequest).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .contains("A category with the same name (vasa) already exists.")
    }
}
