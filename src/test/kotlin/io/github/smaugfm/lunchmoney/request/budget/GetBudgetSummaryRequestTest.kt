package io.github.smaugfm.lunchmoney.request.budget

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudget
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudgetConfig
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudgetData
import io.github.smaugfm.lunchmoney.request.budget.params.LunchmoneyGetBudgetSummaryParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.time.LocalDate
import java.util.Currency

class GetBudgetSummaryRequestTest : TestMockServerBase() {

    @Test
    fun getBudgetSummaryTest() {
        mockServer
            .`when`(
                request("/budgets")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getBudgetSummary.json"))
            )

        val request = LunchmoneyGetBudgetSummaryRequest(
            LunchmoneyGetBudgetSummaryParams(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                Currency.getInstance("USD")
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                listOf(
                    LunchmoneyBudget(
                        categoryName = "Food",
                        categoryId = 34476,
                        categoryGroupName = null,
                        groupId = null,
                        isGroup = true,
                        isIncome = false,
                        excludeFromBudget = false,
                        excludeFromTotals = false,
                        data = mapOf(
                            LocalDate.parse("2020-09-01") to LunchmoneyBudgetData(
                                23,
                                373.51,
                                376.08,
                                376.08,
                                Currency.getInstance("USD"),
                                true
                            ),
                            LocalDate.parse("2020-08-01") to LunchmoneyBudgetData(
                                23,
                                123.92,
                                300.0,
                                300.0,
                                Currency.getInstance("USD")
                            )
                        ),
                        config = LunchmoneyBudgetConfig(
                            9,
                            "monthly",
                            300.0,
                            Currency.getInstance("USD"),
                            300.0,
                            "fixed-rollover"
                        ),
                        order = 0
                    ),
                    LunchmoneyBudget(
                        categoryName = "Alcohol & Bars",
                        categoryId = 26,
                        categoryGroupName = "Food",
                        groupId = 34476,
                        isGroup = false,
                        isIncome = false,
                        excludeFromBudget = false,
                        excludeFromTotals = false,
                        data = mapOf(
                            LocalDate.parse("2020-09-01") to LunchmoneyBudgetData(
                                14,
                                270.86
                            ),
                            LocalDate.parse("2020-08-01") to LunchmoneyBudgetData(
                                8,
                                79.53
                            )
                        ),
                        order = 1
                    )
                )
            )
    }
}
