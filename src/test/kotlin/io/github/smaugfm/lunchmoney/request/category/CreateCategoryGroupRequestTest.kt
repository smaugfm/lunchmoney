package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isSuccess
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyCreateCategoryGroupRequestParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyApiErrorResponse
import io.github.smaugfm.lunchmoney.response.LunchmoneyCreateCategoryResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType

internal class CreateCategoryGroupRequestTest : TestMockServerBase() {
    @Test
    fun createCategoryGroupRequestTest() {
        mockServer.`when`(
            request("/categories/group")
                .withMethod("POST")
                .withContentType(MediaType.APPLICATION_JSON)
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/createCategory.json"))
        )
        val request = LunchmoneyCreateCategoryGroupRequest(
            LunchmoneyCreateCategoryGroupRequestParams("vasa")
        )

        assertThat { api.execute(request).block() }
            .isSuccess().isEqualTo(
                LunchmoneyCreateCategoryResponse(1234L)
            )
    }

    @Test
    fun createCategoryGroupRequestErrorAlreadyExists() {
        mockServer.`when`(
            request("/categories/group")
                .withMethod("POST")
                .withContentType(MediaType.APPLICATION_JSON)
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/createCategory-errorAlreadyExists.json"))
        )
        val createCategoryRequest = LunchmoneyCreateCategoryGroupRequest(
            LunchmoneyCreateCategoryGroupRequestParams("vasa")
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
