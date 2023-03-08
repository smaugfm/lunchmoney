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
import com.github.smaugfm.lunchmoney.model.CategoryDeletionDependency
import com.github.smaugfm.lunchmoney.response.ApiErrorResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response

internal class DeleteCategoryRequestTest : TestMockServerBase() {
    @Test
    fun deleteCategoryRequestTest() {
        val id = 1234L
        mockServer
            .`when`(
                request("/categories/$id")
                    .withMethod("DELETE")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("true")
            )
        val request = DeleteCategoryRequest(
            id
        )
        assertThat(api.execute(request).block())
            .isEqualTo(true)
    }

    @Test
    fun deleteCategoryRequestTestWithDependents() {
        val id = 1234L
        mockServer.`when`(
            request("/categories/$id")
                .withMethod("DELETE")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/deleteCategory-dependents.json"))
        )
        val deleteCategoryRequest = DeleteCategoryRequest(
            id
        )
        assertThat { api.execute(deleteCategoryRequest).block() }
            .isFailure()
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(ApiResponseException::class.java)
            .prop(ApiResponseException::apiErrorResponse)
            .isNotNull()
            .prop(ApiErrorResponse::dependents)
            .isEqualTo(
                CategoryDeletionDependency(
                    "Food & Drink",
                    4L,
                    0L,
                    43L,
                    7L,
                    0L
                )
            )
    }
}
