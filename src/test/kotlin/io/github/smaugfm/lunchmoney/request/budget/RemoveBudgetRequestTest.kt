package io.github.smaugfm.lunchmoney.request.budget

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.request.budget.params.LunchmoneyRemoveBudgetParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.time.LocalDate

class RemoveBudgetRequestTest : TestMockServerBase() {
    @Test
    fun removeBudgetTest() {
        mockServer
            .`when`(
                request("/budgets")
                    .withMethod("DELETE")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody("true")
            )
        val request = LunchmoneyRemoveBudgetRequest(
            LunchmoneyRemoveBudgetParams(
                LocalDate.now(),
                1234L
            )
        )

        assertThat(api.execute(request).block())
            .isEqualTo(
                true
            )
    }
}
