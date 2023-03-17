package io.github.smaugfm.lunchmoney.request.category

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryChild
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.request.category.params.LunchmoneyAddToCategoryGroupsParams
import org.junit.jupiter.api.Test
import java.time.Instant

internal class AddToCategoryGroupRequestTest : TestMockServerBase() {
    @Test
    fun createCategoryRequestTest() {
        val groupId = 315358L
        mockServer.`when`(
            org.mockserver.model.HttpRequest.request("/categories/group/$groupId/add")
                .withMethod("POST")
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
        )
            .respond(
                org.mockserver.model.HttpResponse.response()
                    .withStatusCode(200)
                    .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/addToGroup.json"))
            )
        val request = LunchmoneyAddToCategoryGroupRequest(
            groupId,
            LunchmoneyAddToCategoryGroupsParams(
                listOf(315162L, 315164L, 315169L, 315172L)
            )
        )
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyCategorySingle(
                    315358L,
                    "Food & Drink",
                    null,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    isGroup = true,
                    groupId = null,
                    groupCategoryName = null,
                    children = listOf(
                        LunchmoneyCategoryChild(
                            315162L,
                            "Alcohol, Bars",
                            null,
                            Instant.parse("2022-03-06T20:11:36.066Z")
                        )
                    )
                )
            )
    }
}
