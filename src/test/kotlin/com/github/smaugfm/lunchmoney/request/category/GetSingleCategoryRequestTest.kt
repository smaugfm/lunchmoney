package com.github.smaugfm.lunchmoney.request.category

import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.CategoryChild
import com.github.smaugfm.lunchmoney.model.CategorySingle
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import reactor.test.StepVerifier
import java.time.Instant

internal class GetSingleCategoryRequestTest : TestMockServerBase() {
    @Test
    fun singleCategoryTestSimple() {
        val id = 427755L
        mockServer
            .`when`(
                request("/categories/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("getSingleCategory-simple.json"))
            )
        val getSingleCategoryRequest = GetSingleCategoryRequest(id)
        StepVerifier
            .create(api.execute(getSingleCategoryRequest))
            .expectNext(
                CategorySingle(
                    id,
                    "Shopping",
                    null,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    isGroup = false,
                    groupId = null,
                    groupCategoryName = null,
                    children = null
                )
            ).verifyComplete()
    }

    @Test
    fun singleCategoryTestGroup() {
        val id = 427758L
        mockServer
            .`when`(
                request("/categories/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("getSingleCategory-group.json"))
            )
        val getSingleCategoryRequest = GetSingleCategoryRequest(id)
        StepVerifier
            .create(api.execute(getSingleCategoryRequest))
            .expectNext(
                CategorySingle(
                    id,
                    "Food",
                    "Consumables",
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    isGroup = true,
                    groupId = null,
                    groupCategoryName = null, children = listOf(
                        CategoryChild(
                            427749L,
                            "Coffee Shops",
                            null,
                            Instant.parse("2023-02-02T14:57:43.459Z")
                        ),
                        CategoryChild(
                            427749L,
                            "Coffee Shops",
                            null,
                            Instant.parse("2023-02-02T14:57:43.459Z")
                        )
                    )
                )
            ).verifyComplete()
    }

    @Test
    fun singleCategoryTestSubgroup() {
        val id = 427749L
        mockServer
            .`when`(
                request("/categories/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("getSingleCategory-subgroup.json"))
            )
        val getSingleCategoryRequest = GetSingleCategoryRequest(id)
        StepVerifier
            .create(api.execute(getSingleCategoryRequest))
            .expectNext(
                CategorySingle(
                    id,
                    "Coffee Shops",
                    null,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    isGroup = false,
                    groupId = 427758L,
                    groupCategoryName = "Food",
                    children = null
                )
            ).verifyComplete()
    }
}
