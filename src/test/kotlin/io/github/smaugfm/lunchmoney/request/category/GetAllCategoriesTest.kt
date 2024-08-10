package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategory
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryChild
import io.github.smaugfm.lunchmoney.request.category.params.GetAllCategoriesParams
import io.github.smaugfm.lunchmoney.response.GetAllCategoriesResponse
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
                .withBody(getResourceAsString("response/getAllCategories.json"))
        )
        val request =
            GetAllCategoriesRequest(
                GetAllCategoriesParams(format = GetAllCategoriesParams.Format.Flattened)
            )
        assertThat(api.execute(request).block())
            .isEqualTo(
                GetAllCategoriesResponse(
                    listOf(
                        LunchmoneyCategory(
                            id = 427748L,
                            name = "Alcohol, Bars",
                            description = null,
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            updatedAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            createdAt = Instant.parse("2023-02-02T14:57:43.447Z"),
                            isGroup = false,
                            groupId = null,
                            order = 0
                        ),
                        LunchmoneyCategory(
                            id = 427749L,
                            name = "Coffee Shops",
                            description = null,
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            updatedAt = Instant.parse("2023-02-02T14:57:43.459Z"),
                            createdAt = Instant.parse("2023-02-02T14:57:43.459Z"),
                            isGroup = false,
                            groupId = 427758L,
                            order = 0,
                            children = listOf(
                                LunchmoneyCategoryChild(
                                    id = 315162,
                                    name = "Alcohol, Bars",
                                    description = null,
                                    createdAt = Instant.parse("2022-03-06T20:11:36.066Z")
                                )
                            )
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
                .withBody(getResourceAsString("response/getAllCategories-empty.json"))
        )
        val getAllCategoriesRequest = GetAllCategoriesRequest()
        assertThat(api.execute(getAllCategoriesRequest).block())
            .isEqualTo(GetAllCategoriesResponse(listOf()))
    }
}
