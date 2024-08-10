package io.github.smaugfm.lunchmoney

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isBetween
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import assertk.assertions.size
import io.github.smaugfm.lunchmoney.api.LunchmoneyApi
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategory
import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyUpdateTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import java.math.BigDecimal
import java.time.LocalDate

@EnabledIfEnvironmentVariable(named = "LUNCHMONEY_TEST_TOKEN", matches = "\\w+")
class JsonSchemaUpToDateTest : TestBase() {

    private val api = LunchmoneyApi(System.getenv("LUNCHMONEY_TEST_TOKEN"))

    @Test
    fun getUserTest() {
        val user = api.getCurrentUser().block()!!
        assertThat(user).prop(LunchmoneyUser::userName)
            .isEqualTo("Dmytro Marchuk")
        assertThat(user).prop(LunchmoneyUser::budgetName)
            .isEqualTo("test")
        assertThat(user).prop(LunchmoneyUser::apiKeyLabel)
            .isEqualTo("Github Actions")
    }

    @Test
    fun getAllCategoriesTest() {
        val categories = api.getAllCategories().block()!!
        assertThat(categories)
            .size()
            .isBetween(11, 12)
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
    fun getAllTagsTest() {
        val tags = api.getAllTags().block()!!
        assertThat(tags)
            .hasSize(5)
    }

    @Test
    fun getAllTransactionsTest() {
        val transactions = api.getAllTransactions().block()!!
        assertThat(transactions)
            .size()
            .isGreaterThan(23)
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
        val transactions = api.getAllTransactions().block()!!
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
            .hasSize(6)
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
    fun getAllAssetsTest() {
        val assets = api.getAllAssets().block()!!
        assertThat(assets)
            .hasSize(4)
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
