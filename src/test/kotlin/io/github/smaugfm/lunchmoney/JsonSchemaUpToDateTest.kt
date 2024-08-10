package io.github.smaugfm.lunchmoney

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.cause
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isBetween
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import assertk.assertions.size
import io.github.smaugfm.lunchmoney.api.LunchmoneyApi
import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudget
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudgetData
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategory
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryChild
import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionTag
import io.github.smaugfm.lunchmoney.model.LunchmoneyUpdateTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency

@EnabledIfEnvironmentVariable(named = "LUNCHMONEY_TEST_TOKEN", matches = "\\w+")
class JsonSchemaUpToDateTest : TestBase() {

    private val api = LunchmoneyApi(System.getenv("LUNCHMONEY_TEST_TOKEN"))

    @Test
    fun invalidAuthTest() {
        val api = LunchmoneyApi("invalid")
        assertFailure {
            api.getCurrentUser().block()
        }.isInstanceOf(RuntimeException::class)
            .cause()
            .isNotNull()
            .isInstanceOf(LunchmoneyApiResponseException::class)
            .prop(LunchmoneyApiResponseException::message)
            .isNotNull()
            .contains("Access token does not exist.")
    }

    @Test
    fun getAllAssetsTest() {
        val assets = api.getAllAssets().block()!!
        assertThat(assets)
            .hasSize(4)
        assertThat(assets[0])
            .isEqualTo(
                LunchmoneyAsset(
                    id = 55209,
                    typeName = LunchmoneyAssetType.CREDIT,
                    subtypeName = "credit card",
                    name = "Credit Card (..4977)",
                    displayName = "Penny&#x27;s Visa Card",
                    balance = "-684.6300".toBigDecimal(),
                    balanceAsOf = Instant.parse("2023-07-04T10:17:14Z"),
                    closedOn = null,
                    currency = Currency.getInstance("UAH"),
                    institutionName = "Chase",
                    excludeTransactions = false,
                    createdAt = Instant.parse("2023-07-04T10:17:09.544Z")
                )
            )
    }

    @Test
    fun getBudgetSummaryTest() {
        val budgets = api.getBudgetSummary(
            LocalDate.parse("2023-07-01"),
            LocalDate.parse("2023-07-30")
        ).block()!!
        assertThat(budgets)
            .hasSize(11)
        assertThat(budgets[3])
            .isEqualTo(
                LunchmoneyBudget(
                    categoryName = "Food",
                    categoryId = 489285,
                    categoryGroupName = null,
                    groupId = null,
                    isGroup = true,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    data = mapOf(
                        LocalDate.parse("2023-07-01") to
                            LunchmoneyBudgetData(
                                numTransactions = 9,
                                spendingToBase = 1381.6100000000001,
                                budgetToBase = 430.0,
                                budgetAmount = 430.0,
                                budgetCurrency = Currency.getInstance("UAH"),
                                isAutomated = null
                            )
                    ),
                    config = null,
                    order = 3,
                    archived = false,
                    recurring = null
                )
            )
    }

    @Test
    fun crudBudgetTest() {
        api.removeBudget(
            LocalDate.parse("2023-07-01"),
            489285
        ).block()!!
        api.upsertBudget(
            LocalDate.parse("2023-07-01"),
            489285,
            430.0,
            Currency.getInstance("UAH")
        ).block()
    }

    @Test
    fun getAllCategoriesTest() {
        val categories = api.getAllCategories().block()!!
        assertThat(categories)
            .size()
            .isEqualTo(11)
    }

    @Test
    fun getAllCategoriesNestedTest() {
        val categories = api.getAllCategories(true).block()!!
        assertThat(categories)
            .size()
            .isEqualTo(7)
        assertThat(categories[1])
            .isEqualTo(
                LunchmoneyCategory(
                    id = 489285,
                    name = "Food",
                    description = "Consumables",
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    archived = false,
                    archivedOn = null,
                    updatedAt = Instant.parse("2023-07-04T10:17:01.900Z"),
                    createdAt = Instant.parse("2023-07-04T10:17:01.900Z"),
                    isGroup = true,
                    groupId = null,
                    order = null,
                    children = listOf(
                        LunchmoneyCategoryChild(
                            id = 489276,
                            name = "Coffee Shops",
                            description = null,
                            createdAt = Instant.parse("2023-07-04T10:17:01.599Z")
                        ),
                        LunchmoneyCategoryChild(
                            id = 489279,
                            name = "Food Delivery",
                            description = null,
                            createdAt = Instant.parse("2023-07-04T10:17:01.609Z")
                        ),
                        LunchmoneyCategoryChild(
                            id = 489278,
                            name = "Groceries",
                            description = null,
                            createdAt = Instant.parse("2023-07-04T10:17:01.605Z")
                        ),
                        LunchmoneyCategoryChild(
                            id = 489281,
                            name = "Restaurants",
                            description = null,
                            createdAt = Instant.parse("2023-07-04T10:17:01.614Z")
                        )
                    ),
                    groupCategoryName = null
                )
            )
    }

    @Test
    fun getSingleCategoryTest() {
        val category = api.getSingleCategory(489275).block()!!
        assertThat(category)
            .isEqualTo(
                LunchmoneyCategory(
                    id = 489275,
                    name = "Alcohol, Bars",
                    description = null,
                    isIncome = false,
                    excludeFromBudget = false,
                    excludeFromTotals = false,
                    archived = false,
                    archivedOn = null,
                    updatedAt = null,
                    createdAt = null,
                    isGroup = false,
                    groupId = null,
                    order = null,
                    children = null,
                    groupCategoryName = null
                )
            )
    }

    @Test
    fun crudCategoryTest() {
        val catName = "test-category"
        val catName2 = "test-category2"
        val id = api.createCategory(catName, false, true, true).block()!!
        try {
            var cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategory::id)
                .isEqualTo(id)
            assertThat(cat)
                .prop(LunchmoneyCategory::name)
                .isEqualTo(catName)

            assertThat(api.updateCategory(id, true, false, false, catName2).block()!!).isTrue()
            cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategory::name)
                .isEqualTo(catName2)
        } finally {
            assertThat(api.forceDeleteCategory(id).block()!!).isTrue()
        }
    }

    @Test
    fun crudCategoryGroupTest() {
        val catName = "test-category"
        val catName2 = "test-category2"
        val id = api.createCategoryGroup(catName).block()!!
        try {
            var cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategory::id)
                .isEqualTo(id)
            assertThat(cat)
                .prop(LunchmoneyCategory::name)
                .isEqualTo(catName)

            assertThat(api.updateCategory(id, true, false, false, catName2).block()!!).isTrue()
            cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategory::name)
                .isEqualTo(catName2)
        } finally {
            assertThat(api.forceDeleteCategory(id).block()!!).isTrue()
        }
    }

    @Test
    fun getUserTest() {
        val user = api.getCurrentUser().block()!!
        assertThat(user).prop(LunchmoneyUser::userName)
            .isEqualTo("Dmytro Marchuk")
        assertThat(user).prop(LunchmoneyUser::budgetName)
            .isEqualTo("test")
        assertThat(user).prop(LunchmoneyUser::apiKeyLabel)
            .isEqualTo("local testing")
    }

    @Test
    fun getAllTagsTest() {
        val tags = api.getAllTags().block()!!
        assertThat(tags)
            .hasSize(5)
        assertThat(tags[0])
            .isEqualTo(
                LunchmoneyTransactionTag(
                    id = 54113,
                    name = "Penny&#x27;s",
                    description = "",
                    archived = false
                )
            )
    }

    @Test
    fun getAllTransactionsTest() {
        val transactions = api.getAllTransactions(
            startDate = LocalDate.of(2023, 7, 1),
            endDate = LocalDate.of(2023, 7, 30)
        ).block()!!
        assertThat(transactions)
            .size()
            .isEqualTo(37)
    }

    @Test
    fun crudTransactionTest() {
        val ids = api.insertTransactions(
            listOf(
                LunchmoneyInsertTransaction(LocalDate.now(), BigDecimal.ONE)
            )
        ).block()!!
        assertThat(ids)
            .hasSize(1)
        val transaction = api.getSingleTransaction(ids[0]).block()!!
        assertThat(transaction)
            .prop(LunchmoneyTransaction::id)
            .isEqualTo(ids[0])

        assertThat(
            api.updateTransaction(ids[0], LunchmoneyUpdateTransaction(LocalDate.now()))
                .block()!!.updated
        ).isTrue()
    }

    @Test
    fun crudTransactionGroupTest() {
        val transactions = api.getAllTransactions(
            startDate = LocalDate.of(2023, 7, 1),
            endDate = LocalDate.of(2023, 7, 30)
        ).block()!!
            .filter { it.recurringId == null && it.groupId == null && !it.isGroup }
            .subList(0, 2)
        val id = api.createTransactionGroup(
            LocalDate.now(),
            "vasa",
            transactions.map { it.id }
        ).block()!!
        try {
            val transaction = api.getSingleTransaction(id).block()!!
            assertThat(transaction)
                .prop(LunchmoneyTransaction::id)
                .isEqualTo(id)
        } finally {
            assertThat(api.deleteTransactionGroup(id).block()!!.toSet())
                .isEqualTo(transactions.map { it.id }.toSet())
        }
    }

    @Test
    fun getRecurringExpensesTest() {
        val from = LocalDate.of(2023, 6, 1)
        val exp = api.getRecurringExpenses(from).block()!!
        assertThat(exp)
            .hasSize(4)
    }

    @Test
    fun getBudgetTest() {
        val from = LocalDate.of(2023, 6, 1)
        val to = LocalDate.of(2023, 6, 30)
        val budgets = api.getBudgetSummary(from, to).block()!!
        assertThat(budgets)
            .size()
            .isBetween(11, 12)
    }

    @Test
    fun budgetCrudTest() {
        val catId = 489282L
        val from = LocalDate.of(2023, 6, 1)
        val amount = 123.123

        assertThat(api.upsertBudget(from, catId, amount).block()).isNull()
        assertThat(api.removeBudget(from, catId).block()!!).isTrue()
    }

    @Test
    fun getAllPlaidAccounts() {
        assertThat(api.getAllPlaidAccounts().block()!!).hasSize(0)
    }

    @Test
    fun getAllCryptoAccounts() {
        assertThat(api.getAllCrypto().block()!!).hasSize(0)
    }
}
