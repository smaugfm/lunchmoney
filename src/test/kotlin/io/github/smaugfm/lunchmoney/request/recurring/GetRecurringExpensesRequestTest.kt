package io.github.smaugfm.lunchmoney.request.recurring

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyRecurringExpense
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseSource
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyRecurringExpenseType
import io.github.smaugfm.lunchmoney.request.recurring.params.LunchmoneyGetRecurringExpensesParams
import io.github.smaugfm.lunchmoney.response.LunchmoneyGetRecurringExpensesResponse
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
                .withBody(getResourceAsString("response/getRecurringExpenses.json"))
        )
        val request = LunchmoneyGetRecurringExpensesRequest(
            LunchmoneyGetRecurringExpensesParams(LocalDate.now(), true)
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyGetRecurringExpensesResponse(
                    listOf(
                        LunchmoneyRecurringExpense(
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
                            LunchmoneyRecurringExpenseType.CLEARED,
                            null,
                            LunchmoneyRecurringExpenseSource.MANUAL,
                            null,
                            null,
                            null
                        ),
                        LunchmoneyRecurringExpense(
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
                            LunchmoneyRecurringExpenseType.CLEARED,
                            null,
                            LunchmoneyRecurringExpenseSource.MANUAL,
                            null,
                            null,
                            null
                        )
                    )
                )
            )
    }
}
