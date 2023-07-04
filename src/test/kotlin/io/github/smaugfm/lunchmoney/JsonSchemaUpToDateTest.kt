package io.github.smaugfm.lunchmoney

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.github.smaugfm.lunchmoney.api.LunchmoneyApi
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import reactor.tools.agent.ReactorDebugAgent

@EnabledIfEnvironmentVariable(named = "LUNCHMONEY_TEST_TOKEN", matches = "\\w+")
class JsonSchemaUpToDateTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun initReactor() {
            ReactorDebugAgent.init()
        }
    }

    private val api = LunchmoneyApi(System.getenv("LUNCHMONEY_TEST_TOKEN"))

    @Test
    fun getUserTest() {
        val user = api.getCurrentUser().block()!!
        assertThat(user).prop(LunchmoneyUser::userName)
            .isEqualTo("vasa")
        assertThat(user).prop(LunchmoneyUser::userEmail)
            .isEqualTo("vasa@vasa.com")
        assertThat(user).prop(LunchmoneyUser::budgetName)
            .isEqualTo("test")
        assertThat(user).prop(LunchmoneyUser::apiKeyLabel)
            .isEqualTo("Github Actions")
    }

    @Test
    fun getAllCategoriesTest() {
        val categories = api.getAllCategories().block()!!
        assertThat(categories)
            .hasSize(11)
    }

    @Test
    fun getSingleCategoryTest() {
        val cat = api.getSingleCategory(489281).block()!!
        assertThat(cat)
            .prop(LunchmoneyCategorySingle::id)
            .isEqualTo(489281)
    }

    @Test
    fun crudCategoryTest() {
        val catName = "test-category"
        val catName2 = "test-category2"
        val id = api.createCategory(catName, false, true, true).block()!!
        try {
            var cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategorySingle::id)
                .isEqualTo(id)
            assertThat(cat)
                .prop(LunchmoneyCategorySingle::name)
                .isEqualTo(catName)

            assertThat(api.updateCategory(id, true, false, false, catName2).block()!!).isTrue()
            cat = api.getSingleCategory(id).block()!!
            assertThat(cat)
                .prop(LunchmoneyCategorySingle::name)
                .isEqualTo(catName2)

        } finally {
            assertThat(api.forceDeleteCategory(id).block()!!).isTrue()
        }
    }
}
