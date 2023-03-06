package com.github.smaugfm.lunchmoney.request.budget

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.Budget
import com.github.smaugfm.lunchmoney.model.BudgetConfig
import com.github.smaugfm.lunchmoney.model.BudgetData
import com.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams
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
                    .withBody(getResourceAsString("getBudgetSummary.json"))
            )

        val request = GetBudgetSummaryRequest(
            GetBudgetSummaryParams(
                LocalDate.now(),
                LocalDate.now().plusDays(1)
            )
        )


        assertThat(api.execute(request).block())
            .isEqualTo(
                listOf(
                    Budget(
                        categoryName = "Food",
                        categoryId = 34476,
                        categoryGroupName = null,
                        groupId = null,
                        isGroup = true,
                        isIncome = false,
                        excludeFromBudget = false,
                        excludeFromTotals = false,
                        data = mapOf(
                            LocalDate.parse("2020-09-01") to BudgetData(
                                23,
                                373.51,
                                376.08,
                                376.08,
                                Currency.getInstance("USD"),
                                true,
                            ),
                            LocalDate.parse("2020-08-01") to BudgetData(
                                23,
                                123.92,
                                300.0,
                                300.0,
                                Currency.getInstance("USD")
                            ),
                        ),
                        config = BudgetConfig(
                            9,
                            "monthly",
                            300.0,
                            Currency.getInstance("USD"),
                            300.0,
                            "fixed-rollover"
                        ),
                        order = 0
                    ),
                    Budget(
                        categoryName = "Alcohol & Bars",
                        categoryId = 26,
                        categoryGroupName = "Food",
                        groupId = 34476,
                        isGroup = false,
                        isIncome = false,
                        excludeFromBudget = false,
                        excludeFromTotals = false,
                        data = mapOf(
                            LocalDate.parse("2020-09-01") to BudgetData(
                                14,
                                270.86
                            ),
                            LocalDate.parse("2020-08-01") to BudgetData(
                                8,
                                79.53
                            ),
                        ),
                        order = 1
                    )
                )
            )
    }
}
