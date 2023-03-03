package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.CategoryMultiple
import com.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import reactor.test.StepVerifier
import java.time.Instant

internal class GetAllCategoriesTest : TestMockServerBase() {
    @Test
    fun allCategoriesTest() {
        mockServer.`when`(
            request("/categories")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("getAllCategories.json"))
        )
        val getAllCategoriesRequest = GetAllCategoriesRequest()
        StepVerifier
            .create(api.execute(getAllCategoriesRequest))
            .expectNext(
                GetAllCategoriesResponse(
                    listOf(
                        CategoryMultiple(
                            427748L,
                            "Alcohol, Bars",
                            null,
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            updatedAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            createdAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            isGroup = false,
                            groupId = null
                        ),
                        CategoryMultiple(
                            427749L,
                            "Coffee Shops",
                            null,
                            false,
                            false,
                            false,
                            Instant.parse("2023-02-02T14:57:43.459Z"),
                            Instant.parse("2023-02-02T14:57:43.459Z"),
                            false,
                            427758L
                        )
                    )
                )
            )
            .verifyComplete()
    }

    @Test
    fun allCategoriesEmptyTest() {
        mockServer.`when`(
            request("/categories")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("getAllCategories-empty.json"))
        )
        val getAllCategoriesRequest = GetAllCategoriesRequest()
        StepVerifier
            .create(api.execute(getAllCategoriesRequest))
            .expectNext(GetAllCategoriesResponse(listOf()))
            .verifyComplete()
    }
}
