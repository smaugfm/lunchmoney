package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isSuccess
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.request.category.params.CreateCategoryGroupRequestParams
import io.github.smaugfm.lunchmoney.response.CreateCategoryResponse
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
        val request = CreateCategoryGroupRequest(
            CreateCategoryGroupRequestParams("vasa")
        )

        assertThat { api.execute(request).block() }
            .isSuccess().isEqualTo(
                CreateCategoryResponse(1234L)
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
        val createCategoryRequest = CreateCategoryGroupRequest(
            CreateCategoryGroupRequestParams("vasa")
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
