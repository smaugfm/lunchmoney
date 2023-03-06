package com.github.smaugfm.lunchmoney.request.recurring

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.smaugfm.lunchmoney.TestMockServerBase
import com.github.smaugfm.lunchmoney.Util.getResourceAsString
import com.github.smaugfm.lunchmoney.model.RecurringExpense
import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseSource
import com.github.smaugfm.lunchmoney.model.enumeration.RecurringExpenseType
import com.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import com.github.smaugfm.lunchmoney.response.GetRecurringExpensesResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

class GetRecurringExpensesRequestTest : TestMockServerBase() {
    @Test
    fun getRecurringExpensesTest() {
        mockServer.`when`(
            request("/recurring_expenses")
                .withMethod("GET")
        ).respond(
            response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("getRecurringExpenses.json"))
        )
        val getRecurringExpensesRequest = GetRecurringExpensesRequest(
            GetRecurringExpensesParams(LocalDate.now(), true)
        )

        assertThat(api.execute(getRecurringExpensesRequest).block())
            .isEqualTo(
                GetRecurringExpensesResponse(
                    listOf(
                        RecurringExpense(
                            264,
                            LocalDate.parse("2020-01-01"),
                            null,
                            "twice a month",
                            "Test 5",
                            BigDecimal("-122.0000"),
                            Currency.getInstance("CAD"),
                            Instant.parse("2020-01-30T07:58:43.944Z"),
                            null,
                            LocalDate.parse("2020-01-01"),
                            RecurringExpenseType.CLEARED,
                            null,
                            RecurringExpenseSource.MANUAL,
                            null,
                            null,
                            null
                        ),
                        RecurringExpense(
                            262,
                            LocalDate.parse("2020-01-01"),
                            null,
                            "monthly",
                            "Test 2",
                            BigDecimal("-32.4500"),
                            Currency.getInstance("USD"),
                            Instant.parse("2020-01-30T07:58:43.921Z"),
                            "Test description 2",
                            LocalDate.parse("2020-01-03"),
                            RecurringExpenseType.CLEARED,
                            null,
                            RecurringExpenseSource.MANUAL,
                            null,
                            null,
                            null
                        )
                    )
                )
            )
    }
}
