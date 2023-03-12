package io.github.smaugfm.lunchmoney.request.budget

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import io.github.smaugfm.lunchmoney.response.UpsertBudgetResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.time.LocalDate
import java.util.Currency

class UpsertBudgetRequestTest : TestMockServerBase() {
    @Test
    fun upsertBudgetTest() {
        mockServer
            .`when`(
                request("/budgets")
                    .withMethod("PUT")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/upsertBudget.json"))
            )

        val request = UpsertBudgetRequest(
            UpsertBudgetRequestParams(
                LocalDate.now(),
                1234L,
                1234.234,
                Currency.getInstance("USD")
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                UpsertBudgetResponse(
                    UpsertBudgetResponse.UpsertBudgetCategoryGroupResponse(
                        34476L,
                        100.0,
                        Currency.getInstance("USD"),
                        LocalDate.parse("2021-06-01")
                    )
                )
            )
    }
}
