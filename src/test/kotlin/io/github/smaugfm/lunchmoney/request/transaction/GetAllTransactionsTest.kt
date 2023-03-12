package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.Transaction
import io.github.smaugfm.lunchmoney.model.enumeration.TransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import io.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Currency

internal class GetAllTransactionsTest : TestMockServerBase() {
    @Test
    fun allTransactionsTest() {
        mockServer.`when`(
            request("/transactions").withMethod("GET")
                .withQueryStringParameter("tag_id", "1234")
                .withQueryStringParameter("recurring_id", "1234")
                .withQueryStringParameter("plaid_account_id", "1234")
                .withQueryStringParameter("category_id", "1234")
                .withQueryStringParameter("asset_id", "1234")
                .withQueryStringParameter("group_id", "1234")
                .withQueryStringParameter("is_group", "false")
                .withQueryStringParameter("status", "recurring")
                .withQueryStringParameter("offset", "1234")
                .withQueryStringParameter("limit", "1234")
                .withQueryStringParameter("start_date", "2020-12-20")
                .withQueryStringParameter("end_date", "2020-12-20")
                .withQueryStringParameter("debit_as_negative", "false")
                .withQueryStringParameter("pending", "false")
        ).respond(
            response().withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                .withBody(getResourceAsString("response/getAllTransactions.json"))
        )
        val request = GetAllTransactionsRequest(
            GetAllTransactionsParams(
                tagId = 1234L,
                recurringId = 1234L,
                plaidAccountId = 1234L,
                categoryId = 1234L,
                assetId = 1234L,
                groupId = 1234L,
                isGroup = false,
                status = TransactionStatus.RECURRING,
                offset = 1234L,
                limit = 1234L,
                startDate = LocalDate.of(2020, 12, 20),
                endDate = LocalDate.of(2020, 12, 20),
                debitAsNegative = false,
                pending = false
            )
        )
        assertThat(api.execute(request).block())
            .isEqualTo(
                GetAllTransactionsResponse(
                    listOf(
                        Transaction(
                            id = 602L,
                            date = LocalDate.of(2020, 1, 1),
                            payee = "Starbucks",
                            amount = BigDecimal("4.5000"),
                            currency = Currency.getInstance("CAD"),
                            toBase = 4.5,
                            notes = "Frappuccino",
                            categoryId = null,
                            assetId = null,
                            recurringId = null,
                            plaidAccountId = null,
                            status = TransactionStatus.CLEARED,
                            parentId = null,
                            isGroup = false,
                            groupId = null,
                            externalId = null,
                            tags = null,
                            originalName = "STARBUCKS NW 32804",
                            type = null,
                            subtype = null,
                            fees = null,
                            price = null,
                            quantity = null
                        ),
                        Transaction(
                            id = 603L,
                            date = LocalDate.of(2020, 1, 2),
                            payee = "Walmart",
                            amount = BigDecimal("20.9100"),
                            currency = Currency.getInstance("USD"),
                            toBase = 20.91,
                            notes = null,
                            categoryId = null,
                            assetId = 153L,
                            recurringId = null,
                            plaidAccountId = null,
                            status = TransactionStatus.UNCLEARED,
                            parentId = null,
                            isGroup = false,
                            groupId = null,
                            externalId = "jf2r3t98o943",
                            tags = null,
                            originalName = "Walmart Superstore ON 39208",
                            type = null,
                            subtype = null,
                            fees = null,
                            price = null,
                            quantity = null
                        )
                    )
                )
            )
    }
}
