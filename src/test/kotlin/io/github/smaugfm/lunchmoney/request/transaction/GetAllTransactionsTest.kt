package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionChild
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionTag
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import io.github.smaugfm.lunchmoney.response.GetAllTransactionsResponse
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
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
                .withQueryStringParameter("status", "cleared")
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
                status = LunchmoneyTransactionStatus.CLEARED,
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
                        LunchmoneyTransaction(
                            id = 246946944,
                            date = LocalDate.parse("2023-07-18"),
                            amount = BigDecimal.valueOf(53.19),
                            currency = Currency.getInstance("USD"),
                            toBase = 53.19,
                            payee = "Amazon",
                            categoryId = 315172,
                            categoryName = "Restaurants",
                            categoryGroupId = 315358,
                            categoryGroupName = "Food & Drink",
                            isIncome = false,
                            excludeFromBudget = false,
                            excludeFromTotals = false,
                            createdAt = Instant.parse("2023-09-09T08:43:05.875Z"),
                            updatedAt = Instant.parse("2023-10-09T06:07:03.105Z"),
                            status = LunchmoneyTransactionStatus.CLEARED,
                            isPending = false,
                            notes = null,
                            originalName = null,
                            recurringId = null,
                            recurringPayee = null,
                            recurringDescription = null,
                            recurringCadence = null,
                            recurringType = null,
                            recurringAmount = null,
                            recurringCurrency = null,
                            parentId = 225508713,
                            hasChildren = false,
                            groupId = null,
                            isGroup = false,
                            assetId = null,
                            assetInstitutionName = null,
                            assetName = null,
                            assetDisplayName = null,
                            assetStatus = null,
                            plaidAccountId = 76602,
                            plaidAccountName = "Amazon Whole Foods Visa",
                            plaidAccountMask = 6299,
                            institutionName = "Chase",
                            plaidAccountDisplayName = "Amazon Whole Foods Visa",
                            plaidMetadata = null,
                            plaidCategory = null,
                            source = null,
                            displayName = "Amazon",
                            displayNotes = null,
                            accountDisplayName = "Amazon Whole Foods Visa",
                            tags = listOf(LunchmoneyTransactionTag(76543, "Amazon")),
                            children = listOf(
                                LunchmoneyTransactionChild(
                                    id = 246946948,
                                    payee = "Child Transaction One",
                                    amount = BigDecimal.valueOf(-33.6),
                                    currency = Currency.getInstance("CAD"),
                                    date = LocalDate.parse("2023-08-10"),
                                    formattedDate = LocalDate.parse("2023-09-10"),
                                    notes = null,
                                    assetId = 7409,
                                    plaidAccountId = null,
                                    toBase = -33.6
                                )
                            )
                        ),
                    ),
                    hasMore = true
                )
            )
    }
}
