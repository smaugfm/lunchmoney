package io.github.smaugfm.lunchmoney.request.category

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryDeletionDependency
import kotlinx.serialization.json.Json
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
        assertFailure { api.execute(deleteCategoryRequest).block() }
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class.java)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .transform {
                Json.decodeFromString<LunchmoneyCategoryDeletionDependency>(it)
            }.isEqualTo(
                LunchmoneyCategoryDeletionDependency(
                    "Food & Drink",
                    4L,
                    0L,
                    43L,
                    7L,
                    0
                )
            )
    }

    @Test
    fun deleteCategoryRequestTestWithDependentsMultiple() {
        val id = 1234L
        mockServer.`when`(
            request("/categories/$id")
                .withMethod("DELETE")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/deleteCategory-dependents-multiple.json"))

        )
        val deleteCategoryRequest = DeleteCategoryRequest(
            id
        )
        assertFailure { api.execute(deleteCategoryRequest).block() }
            .isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class.java)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .transform {
                Json.decodeFromString<List<LunchmoneyCategoryDeletionDependency>>(it)
            }.isEqualTo(
                listOf(
                    LunchmoneyCategoryDeletionDependency(
                        "Food & Drink",
                        4L,
                        0L,
                        43L,
                        7L,
                        0
                    ),
                    LunchmoneyCategoryDeletionDependency(
                        "Food & Drink",
                        4L,
                        0L,
                        43L,
                        7L,
                        0
                    )
                )
            )
    }
}
