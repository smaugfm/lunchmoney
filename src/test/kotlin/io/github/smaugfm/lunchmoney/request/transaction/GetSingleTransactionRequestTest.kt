package io.github.smaugfm.lunchmoney.request.transaction

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.smaugfm.lunchmoney.TestMockServerBase
import io.github.smaugfm.lunchmoney.Util.getResourceAsString
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionChild
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionSource
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams
import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

internal class GetSingleTransactionRequestTest : TestMockServerBase() {
    @Test
    fun singleTransactionTestSimple() {
        val id = 602L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getSingleTransaction.json"))
            )
        val request = GetSingleTransactionRequest(id)
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyTransaction(
                    id = 480887173,
                    date = LocalDate.of(2023, 11, 29),
                    payee = "Walmart",
                    amount = BigDecimal("-14.1800"),
                    currency = Currency.getInstance("USD"),
                    toBase = -14.18,
                    categoryId = 315295L,
                    categoryName = "Health, Medical",
                    categoryGroupId = 315357,
                    categoryGroupName = "Personal",
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    createdAt = Instant.parse("2023-11-30T22:10:57.820Z"),
                    updatedAt = Instant.parse("2023-11-30T23:59:56.587Z"),
                    status = LunchmoneyTransactionStatus.CLEARED,
                    isPending = false,
                    notes = null,
                    originalName = "Walmart",
                    recurringId = null,
                    recurringPayee = null,
                    recurringDescription = null,
                    recurringCadence = null,
                    recurringType = null,
                    recurringAmount = null,
                    recurringCurrency = null,
                    parentId = null,
                    hasChildren = null,
                    groupId = 481307164,
                    isGroup = false,
                    assetId = null,
                    assetInstitutionName = null,
                    assetName = null,
                    assetDisplayName = null,
                    assetStatus = null,
                    plaidAccountId = 54174,
                    plaidAccountName = "Amex 1002",
                    plaidAccountMask = 1005,
                    institutionName = "American Express",
                    plaidAccountDisplayName = "Amex Plat",
                    plaidMetadata = "{\"account_id\":\"fMKfypkyRXSXvpJor4vPTg6OP7wD4afmEjv6N\",\"account_owner\":\"1005\",\"amount\":-14.18,\"authorized_date\":\"2023-11-28\",\"authorized_datetime\":null,\"category\":[\"Shops\",\"Supermarkets and Groceries\"],\"category_id\":\"19047000\",\"check_number\":null,\"counterparties\":[{\"confidence_level\":\"VERY_HIGH\",\"entity_id\":\"O5W5j4dN9OR3E6ypQmjdkWZZRoXEzVMz2ByWM\",\"logo_url\":\"https://plaid-merchant-logos.plaid.com/walmart_1100.png\",\"name\":\"Walmart\",\"type\":\"merchant\",\"website\":\"walmart.com\"}],\"date\":\"2023-11-29\",\"datetime\":null,\"iso_currency_code\":\"USD\",\"location\":{\"address\":null,\"city\":null,\"country\":null,\"lat\":null,\"lon\":null,\"postal_code\":null,\"region\":null,\"store_number\":null},\"logo_url\":\"https://plaid-merchant-logos.plaid.com/walmart_1100.png\",\"merchant_entity_id\":\"O5W5j4dN9OR3E6ypQmjdkWZZRoXEzVMz2ByWM\",\"merchant_name\":\"Walmart\",\"name\":\"Walmart\",\"payment_channel\":\"other\",\"payment_meta\":{\"by_order_of\":null,\"payee\":null,\"payer\":null,\"payment_method\":null,\"payment_processor\":null,\"ppd_id\":null,\"reason\":null,\"reference_number\":\"320233330735688096\"},\"pending\":false,\"pending_transaction_id\":null,\"personal_finance_category\":{\"confidence_level\":\"VERY_HIGH\",\"detailed\":\"GENERAL_MERCHANDISE_SUPERSTORES\",\"primary\":\"GENERAL_MERCHANDISE\"},\"personal_finance_category_icon_url\":\"https://plaid-category-icons.plaid.com/PFC_GENERAL_MERCHANDISE.png\",\"transaction_code\":null,\"transaction_id\":\"rmQdnefvAndbfHN5mZ4y703C3vdjk7mozCw1OarL\",\"transaction_type\":\"place\",\"unofficial_currency_code\":null,\"website\":\"walmart.com\"}",
                    plaidCategory = "GENERAL_MERCHANDISE_SUPERSTORES",
                    source = LunchmoneyTransactionSource.Plaid,
                    displayName = "Walmart",
                    displayNotes = null,
                    accountDisplayName = "Amex Plat",
                    tags = emptyList()
                )
            )
    }

    @Test
    fun singleTransactionTestSimpleWithQuery() {
        val id = 602L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withQueryStringParameter("debit_as_negative", "false")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getSingleTransaction.json"))
            )
        val getSingleTransactionRequest = GetSingleTransactionRequest(
            id,
            GetSingleTransactionParams(false)
        )
        assertThat(api.execute(getSingleTransactionRequest).block())
            .isEqualTo(
                LunchmoneyTransaction(
                    id = 480887173,
                    date = LocalDate.of(2023, 11, 29),
                    payee = "Walmart",
                    amount = BigDecimal("-14.1800"),
                    currency = Currency.getInstance("USD"),
                    toBase = -14.18,
                    categoryId = 315295L,
                    categoryName = "Health, Medical",
                    categoryGroupId = 315357,
                    categoryGroupName = "Personal",
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    createdAt = Instant.parse("2023-11-30T22:10:57.820Z"),
                    updatedAt = Instant.parse("2023-11-30T23:59:56.587Z"),
                    status = LunchmoneyTransactionStatus.CLEARED,
                    isPending = false,
                    notes = null,
                    originalName = "Walmart",
                    recurringId = null,
                    recurringPayee = null,
                    recurringDescription = null,
                    recurringCadence = null,
                    recurringType = null,
                    recurringAmount = null,
                    recurringCurrency = null,
                    parentId = null,
                    hasChildren = null,
                    groupId = 481307164,
                    isGroup = false,
                    assetId = null,
                    assetInstitutionName = null,
                    assetName = null,
                    assetDisplayName = null,
                    assetStatus = null,
                    plaidAccountId = 54174,
                    plaidAccountName = "Amex 1002",
                    plaidAccountMask = 1005,
                    institutionName = "American Express",
                    plaidAccountDisplayName = "Amex Plat",
                    plaidMetadata = "{\"account_id\":\"fMKfypkyRXSXvpJor4vPTg6OP7wD4afmEjv6N\",\"account_owner\":\"1005\",\"amount\":-14.18,\"authorized_date\":\"2023-11-28\",\"authorized_datetime\":null,\"category\":[\"Shops\",\"Supermarkets and Groceries\"],\"category_id\":\"19047000\",\"check_number\":null,\"counterparties\":[{\"confidence_level\":\"VERY_HIGH\",\"entity_id\":\"O5W5j4dN9OR3E6ypQmjdkWZZRoXEzVMz2ByWM\",\"logo_url\":\"https://plaid-merchant-logos.plaid.com/walmart_1100.png\",\"name\":\"Walmart\",\"type\":\"merchant\",\"website\":\"walmart.com\"}],\"date\":\"2023-11-29\",\"datetime\":null,\"iso_currency_code\":\"USD\",\"location\":{\"address\":null,\"city\":null,\"country\":null,\"lat\":null,\"lon\":null,\"postal_code\":null,\"region\":null,\"store_number\":null},\"logo_url\":\"https://plaid-merchant-logos.plaid.com/walmart_1100.png\",\"merchant_entity_id\":\"O5W5j4dN9OR3E6ypQmjdkWZZRoXEzVMz2ByWM\",\"merchant_name\":\"Walmart\",\"name\":\"Walmart\",\"payment_channel\":\"other\",\"payment_meta\":{\"by_order_of\":null,\"payee\":null,\"payer\":null,\"payment_method\":null,\"payment_processor\":null,\"ppd_id\":null,\"reason\":null,\"reference_number\":\"320233330735688096\"},\"pending\":false,\"pending_transaction_id\":null,\"personal_finance_category\":{\"confidence_level\":\"VERY_HIGH\",\"detailed\":\"GENERAL_MERCHANDISE_SUPERSTORES\",\"primary\":\"GENERAL_MERCHANDISE\"},\"personal_finance_category_icon_url\":\"https://plaid-category-icons.plaid.com/PFC_GENERAL_MERCHANDISE.png\",\"transaction_code\":null,\"transaction_id\":\"rmQdnefvAndbfHN5mZ4y703C3vdjk7mozCw1OarL\",\"transaction_type\":\"place\",\"unofficial_currency_code\":null,\"website\":\"walmart.com\"}",
                    plaidCategory = "GENERAL_MERCHANDISE_SUPERSTORES",
                    source = LunchmoneyTransactionSource.Plaid,
                    displayName = "Walmart",
                    displayNotes = null,
                    accountDisplayName = "Amex Plat",
                    tags = emptyList()
                )
            )
    }

    @Test
    fun singleTransactionTestSimple2() {
        val id = 602L
        mockServer
            .`when`(
                request("/transactions/$id")
                    .withMethod("GET")
            ).respond(
                response()
                    .withStatusCode(200)
                    .withContentType(MediaType.APPLICATION_JSON_UTF_8)
                    .withBody(getResourceAsString("response/getSingleTransaction2.json"))
            )
        val request = GetSingleTransactionRequest(id)
        assertThat(api.execute(request).block())
            .isEqualTo(
                LunchmoneyTransaction(
                    id = 2225874632,
                    date = LocalDate.parse("2024-08-10"),
                    amount = BigDecimal.valueOf(37.09),
                    currency = Currency.getInstance("UAH"),
                    toBase = 37.09,
                    payee = "vasa",
                    categoryId = null,
                    categoryName = null,
                    categoryGroupId = null,
                    categoryGroupName = null,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    createdAt = Instant.parse("2024-08-10T14:49:15.465Z"),
                    updatedAt = Instant.parse("2024-08-10T14:49:15.465Z"),
                    status = LunchmoneyTransactionStatus.CLEARED,
                    isPending = false,
                    hasChildren = false,
                    isGroup = true,
                    source = LunchmoneyTransactionSource.Api,
                    displayName = "vasa",
                    accountDisplayName = "",
                    tags = listOf(),
                    children = listOf(
                        LunchmoneyTransactionChild(
                            id = 220527530,
                            payee = "Farmer&#x27;s Market",
                            amount = BigDecimal.valueOf(36.09),
                            currency = Currency.getInstance("UAH"),
                            date = LocalDate.parse("2023-07-04"),
                            formattedDate = LocalDate.parse("2023-07-04"),
                            notes = "Jenny&#x27;s Potluck",
                            assetId = 55209,
                            plaidAccountId = null,
                            toBase = 36.09
                        ),
                        LunchmoneyTransactionChild(
                            id = 220556457,
                            payee = null,
                            amount = BigDecimal.valueOf(1.0),
                            currency = Currency.getInstance("UAH"),
                            date = LocalDate.parse("2023-07-04"),
                            formattedDate = LocalDate.parse("2023-07-04"),
                            notes = null,
                            assetId = null,
                            plaidAccountId = null,
                            toBase = 1.0
                        )
                    )
                )
            )
    }
}
