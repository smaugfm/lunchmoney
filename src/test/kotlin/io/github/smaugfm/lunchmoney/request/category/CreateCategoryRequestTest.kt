package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyCreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyApiErrorResponse
import io.github.smaugfm.lunchmoney.response.LunchmoneyCreateCategoryResponse
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
        val request = LunchmoneyCreateCategoryRequest(
            LunchmoneyCreateUpdateCategoryRequestParams("vasa")
        )
        assertThat(api.execute(request).block())
            .isEqualTo(LunchmoneyCreateCategoryResponse(1234L))
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
        val createCategoryRequest = LunchmoneyCreateCategoryRequest(
            LunchmoneyCreateUpdateCategoryRequestParams("vasa")
        )
        assertThat { api.execute(createCategoryRequest).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::apiErrorResponse)
            .isNotNull()
            .prop(LunchmoneyApiErrorResponse::error)
            .isEqualTo(listOf("A category with the same name (vasa) already exists."))
    }
}
