package com.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.CategoryMultiple
import com.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
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
        assertThat(api.execute(getAllCategoriesRequest).block())
            .isEqualTo(
                GetAllCategoriesResponse(
                    listOf(
                        CategoryMultiple(
                            id = 427748L,
                            name = "Alcohol, Bars",
                            description = null,
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            updatedAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            createdAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            isGroup = false,
                            groupId = null
                        ),
                        CategoryMultiple(
                            id = 427749L,
                            name = "Coffee Shops",
                            description = null,
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            updatedAt = Instant.parse("2023-02-02T14:57:43.459Z"),
                            createdAt = Instant.parse("2023-02-02T14:57:43.459Z"),
                            isGroup = false,
                            groupId = 427758L
                        )
                    )
                )
            )
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
        assertThat(api.execute(getAllCategoriesRequest).block())
            .isEqualTo(GetAllCategoriesResponse(listOf()))
    }
}
