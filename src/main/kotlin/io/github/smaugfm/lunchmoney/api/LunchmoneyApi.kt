package io.github.smaugfm.lunchmoney.api

import io.github.smaugfm.lunchmoney.model.LunchmoneyAsset
import io.github.smaugfm.lunchmoney.model.LunchmoneyBudget
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryMultiple
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategorySingle
import io.github.smaugfm.lunchmoney.model.LunchmoneyCrypto
import io.github.smaugfm.lunchmoney.model.LunchmoneyInsertTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyPlaidAccount
import io.github.smaugfm.lunchmoney.model.LunchmoneyRecurringExpense
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionSplit
import io.github.smaugfm.lunchmoney.model.LunchmoneyTransactionTag
import io.github.smaugfm.lunchmoney.model.LunchmoneyUpdateTransaction
import io.github.smaugfm.lunchmoney.model.LunchmoneyUser
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyAssetType
import io.github.smaugfm.lunchmoney.model.enumeration.LunchmoneyTransactionStatus
import io.github.smaugfm.lunchmoney.request.asset.CreateAssetRequest
import io.github.smaugfm.lunchmoney.request.asset.GetAllAssetsRequest
import io.github.smaugfm.lunchmoney.request.asset.UpdateAssetRequest
import io.github.smaugfm.lunchmoney.request.asset.params.CreateUpdateAssetParams
import io.github.smaugfm.lunchmoney.request.budget.GetBudgetSummaryRequest
import io.github.smaugfm.lunchmoney.request.budget.RemoveBudgetRequest
import io.github.smaugfm.lunchmoney.request.budget.UpsertBudgetRequest
import io.github.smaugfm.lunchmoney.request.budget.params.GetBudgetSummaryParams
import io.github.smaugfm.lunchmoney.request.budget.params.RemoveBudgetParams
import io.github.smaugfm.lunchmoney.request.budget.params.UpsertBudgetRequestParams
import io.github.smaugfm.lunchmoney.request.category.AddToCategoryGroupRequest
import io.github.smaugfm.lunchmoney.request.category.CreateCategoryGroupRequest
import io.github.smaugfm.lunchmoney.request.category.CreateCategoryRequest
import io.github.smaugfm.lunchmoney.request.category.DeleteCategoryRequest
import io.github.smaugfm.lunchmoney.request.category.ForceDeleteCategoryRequest
import io.github.smaugfm.lunchmoney.request.category.GetAllCategoriesRequest
import io.github.smaugfm.lunchmoney.request.category.GetSingleCategoryRequest
import io.github.smaugfm.lunchmoney.request.category.UpdateCategoryRequest
import io.github.smaugfm.lunchmoney.request.category.params.AddToCategoryGroupsParams
import io.github.smaugfm.lunchmoney.request.category.params.CreateCategoryGroupRequestParams
import io.github.smaugfm.lunchmoney.request.category.params.CreateUpdateCategoryRequestParams
import io.github.smaugfm.lunchmoney.request.crypto.GetAllCryptoRequest
import io.github.smaugfm.lunchmoney.request.crypto.UpdateManualCryptoAsset
import io.github.smaugfm.lunchmoney.request.crypto.params.UpdateManualCryptoParams
import io.github.smaugfm.lunchmoney.request.plaid.GetAllPlaidAccountsRequest
import io.github.smaugfm.lunchmoney.request.recurring.GetRecurringExpensesRequest
import io.github.smaugfm.lunchmoney.request.recurring.params.GetRecurringExpensesParams
import io.github.smaugfm.lunchmoney.request.tag.GetAllTagsRequest
import io.github.smaugfm.lunchmoney.request.transaction.CreateTransactionGroupRequest
import io.github.smaugfm.lunchmoney.request.transaction.DeleteTransactionGroupRequest
import io.github.smaugfm.lunchmoney.request.transaction.GetAllTransactionsRequest
import io.github.smaugfm.lunchmoney.request.transaction.GetSingleTransactionRequest
import io.github.smaugfm.lunchmoney.request.transaction.InsertTransactionsRequest
import io.github.smaugfm.lunchmoney.request.transaction.UnsplitTransactionsRequest
import io.github.smaugfm.lunchmoney.request.transaction.UpdateTransactionRequest
import io.github.smaugfm.lunchmoney.request.transaction.params.CreateTransactionGroupParams
import io.github.smaugfm.lunchmoney.request.transaction.params.GetAllTransactionsParams
import io.github.smaugfm.lunchmoney.request.transaction.params.GetSingleTransactionParams
import io.github.smaugfm.lunchmoney.request.transaction.params.InsertTransactionRequestParams
import io.github.smaugfm.lunchmoney.request.transaction.params.UnsplitTransactionsParams
import io.github.smaugfm.lunchmoney.request.transaction.params.UpdateTransactionParams
import io.github.smaugfm.lunchmoney.request.user.GetCurrentUserRequest
import io.github.smaugfm.lunchmoney.response.LunchmoneyUpdateTransactionResponse
import io.github.smaugfm.lunchmoney.response.UpsertBudgetCategoryGroupResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonBuilder
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.netty.resources.ConnectionProvider
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.Currency
import java.util.function.Function

/**
 * Non-blocking Reactor-based Lunchmoney API
 *
 * Documentation on specific parameters or endpoints is available at [lunchmoney.dev](https://lunchmoney.dev)
 */
@ExperimentalSerializationApi
class LunchmoneyApi internal constructor(
    token: String,
    baseUrl: String,
    port: Int,
    jsonBuilderCustomizer: List<JsonBuilder.() -> Unit> = listOf(DEFAULT_JSON_BUILDER),
    reactorNettyConnectionProvider: ConnectionProvider? = null,
    requestTransformer: Function<Publisher<Any>, Publisher<Any>>? = null
) : LunchmoneyApiInternal(
    token,
    baseUrl,
    port,
    jsonBuilderCustomizer,
    reactorNettyConnectionProvider,
    requestTransformer
) {
    /**
     * @param token developer [token](https://lunchmoney.dev/#authentication)
     * @param jsonBuilderCustomizer customize internal [kotlinx.serialization.json.Json] instance
     * @param reactorNettyConnectionProvider customize internal netty [reactor.netty.http.client.HttpClient]
     * @param requestTransformer applies through [Mono.transformDeferred] to all resulting [Mono]'s
     */
    constructor(
        token: String,
        jsonBuilderCustomizer: JsonBuilder.() -> Unit = {},
        reactorNettyConnectionProvider: ConnectionProvider? = null,
        requestTransformer: Function<Publisher<Any>, Publisher<Any>>? = null
    ) : this(
        token,
        LUNCHMONEY_DEV_BASE_URL,
        DEFAULT_HTTP_PORT,
        listOf(jsonBuilderCustomizer, DEFAULT_JSON_BUILDER),
        reactorNettyConnectionProvider,
        requestTransformer
    )

    fun createAsset(
        name: String,
        typeName: LunchmoneyAssetType,
        balance: BigDecimal,
        subtypeName: String? = null,
        displayName: String? = null,
        balanceAsOf: Instant? = null,
        currency: Currency? = null,
        institutionName: String? = null,
        closedOn: LocalDate? = null,
        excludeTransactions: Boolean? = null
    ): Mono<LunchmoneyAsset> = execute(
        CreateAssetRequest(
            CreateUpdateAssetParams(
                typeName = typeName,
                subtypeName = subtypeName,
                name = name,
                displayName = displayName,
                balance = balance,
                balanceAsOf = balanceAsOf,
                currency = currency,
                institutionName = institutionName,
                closedOn = closedOn,
                excludeTransactions = excludeTransactions
            )
        )
    )

    fun getAllAssets(): Mono<List<LunchmoneyAsset>> = execute(GetAllAssetsRequest())
        .map { it.assets }

    fun updateAsset(
        assetId: Long,
        typeName: LunchmoneyAssetType? = null,
        subtypeName: String? = null,
        name: String? = null,
        displayName: String? = null,
        balance: BigDecimal? = null,
        balanceAsOf: Instant? = null,
        currency: Currency? = null,
        institutionName: String? = null,
        closedOn: LocalDate? = null,
        excludeTransactions: Boolean? = null
    ): Mono<LunchmoneyAsset> = execute(
        UpdateAssetRequest(
            assetId,
            CreateUpdateAssetParams(
                typeName = typeName,
                subtypeName = subtypeName,
                name = name,
                displayName = displayName,
                balance = balance,
                balanceAsOf = balanceAsOf,
                currency = currency,
                institutionName = institutionName,
                closedOn = closedOn,
                excludeTransactions = excludeTransactions
            )
        )
    )

    fun getBudgetSummary(
        startDate: LocalDate,
        endDate: LocalDate,
        currency: Currency? = null
    ): Mono<List<LunchmoneyBudget>> = execute(
        GetBudgetSummaryRequest(
            GetBudgetSummaryParams(
                startDate = startDate,
                endDate = endDate,
                currency = currency
            )
        )
    )

    fun removeBudget(
        startDate: LocalDate,
        categoryId: Long
    ): Mono<Boolean> = execute(
        RemoveBudgetRequest(
            RemoveBudgetParams(
                startDate = startDate,
                categoryId = categoryId
            )
        )
    )

    fun upsertBudget(
        startDate: LocalDate,
        categoryId: Long,
        amount: Double,
        currency: Currency? = null
    ): Mono<UpsertBudgetCategoryGroupResponse?> = execute(
        UpsertBudgetRequest(
            UpsertBudgetRequestParams(
                startDate = startDate,
                categoryId = categoryId,
                amount = amount,
                currency = currency
            )
        )
    ).mapNotNull { it.categoryGroup }

    fun addToCategoryGroup(
        groupId: Long,
        categoryIds: List<Long>? = null,
        newCategories: List<String>? = null
    ): Mono<LunchmoneyCategorySingle> = execute(
        AddToCategoryGroupRequest(
            groupId,
            AddToCategoryGroupsParams(
                categoryIds = categoryIds,
                newCategories = newCategories
            )
        )
    )

    fun createCategoryGroup(
        name: String,
        description: String? = null,
        isIncome: Boolean? = null,
        excludeFromBudget: Boolean? = null,
        excludeFromTotals: Boolean? = null,
        categoryIds: List<Long>? = null,
        newCategories: List<String>? = null
    ): Mono<Long> = execute(
        CreateCategoryGroupRequest(
            CreateCategoryGroupRequestParams(
                name = name,
                description = description,
                isIncome = isIncome,
                excludeFromBudget = excludeFromBudget,
                excludeFromTotals = excludeFromTotals,
                categoryIds = categoryIds,
                newCategories = newCategories
            )
        )
    ).map { it.categoryId }

    fun createCategory(
        name: String,
        isIncome: Boolean,
        excludeFromBudget: Boolean,
        excludeFromTotals: Boolean,
        description: String? = null,
        categoryIds: List<Long>? = null,
        groupId: Long? = null
    ): Mono<Long> = execute(
        CreateCategoryRequest(
            CreateUpdateCategoryRequestParams(
                name = name,
                description = description,
                isIncome = isIncome,
                excludeFromBudget = excludeFromBudget,
                excludeFromTotals = excludeFromTotals,
                categoryIds = categoryIds,
                groupId = groupId
            )
        )
    ).map { it.categoryId }

    fun deleteCategory(categoryId: Long): Mono<Boolean> = execute(
        DeleteCategoryRequest(categoryId)
    )

    fun forceDeleteCategory(categoryId: Long): Mono<Boolean> = execute(
        ForceDeleteCategoryRequest(categoryId)
    )

    fun getAllCategories(): Mono<List<LunchmoneyCategoryMultiple>> = execute(
        GetAllCategoriesRequest()
    ).map { it.categories }

    fun getSingleCategory(categoryId: Long): Mono<LunchmoneyCategorySingle> = execute(
        GetSingleCategoryRequest(categoryId)
    )

    fun updateCategory(
        categoryId: Long,
        isIncome: Boolean,
        excludeFromBudget: Boolean,
        excludeFromTotals: Boolean,
        name: String? = null,
        description: String? = null,
        categoryIds: List<Long>? = null,
        groupId: Long? = null
    ): Mono<Boolean> = execute(
        UpdateCategoryRequest(
            categoryId,
            CreateUpdateCategoryRequestParams(
                name = name,
                isIncome = isIncome,
                description = description,
                excludeFromBudget = excludeFromBudget,
                excludeFromTotals = excludeFromTotals,
                categoryIds = categoryIds,
                groupId = groupId
            )
        )
    )

    fun updateManualCryptoAsset(
        cryptoAssetId: Long,
        name: String? = null,
        displayName: String? = null,
        institutionName: String? = null,
        currency: String? = null,
        balance: BigDecimal? = null
    ): Mono<LunchmoneyCrypto> = execute(
        UpdateManualCryptoAsset(
            cryptoAssetId,
            UpdateManualCryptoParams(
                name = name,
                displayName = displayName,
                institutionName = institutionName,
                balance = balance,
                currency = currency
            )
        )
    )

    fun getAllCrypto(): Mono<List<LunchmoneyCrypto>> = execute(
        GetAllCryptoRequest()
    ).map { it.crypto }

    fun getAllPlaidAccounts(): Mono<List<LunchmoneyPlaidAccount>> = execute(
        GetAllPlaidAccountsRequest()
    ).map { it.plaidAccounts }

    fun getRecurringExpenses(
        startDate: LocalDate? = null,
        debitAsNegative: Boolean? = null
    ): Mono<List<LunchmoneyRecurringExpense>> = execute(
        GetRecurringExpensesRequest(
            GetRecurringExpensesParams(
                startDate = startDate,
                debitAsNegative = debitAsNegative
            )
        )
    ).map { it.recurringExpenses }

    fun getAllTags(): Mono<List<LunchmoneyTransactionTag>> = execute(
        GetAllTagsRequest()
    )

    fun createTransactionGroup(
        date: LocalDate,
        payee: String,
        transactions: List<Long>,
        categoryId: Long? = null,
        notes: String? = null,
        tags: List<LunchmoneyTransactionTag>? = null
    ): Mono<Long> = execute(
        CreateTransactionGroupRequest(
            CreateTransactionGroupParams(
                date = date,
                payee = payee,
                transactions = transactions,
                categoryId = categoryId,
                notes = notes,
                tags = tags
            )
        )
    )

    fun deleteTransactionGroup(transactionGroupId: Long): Mono<List<Long>> = execute(
        DeleteTransactionGroupRequest(transactionGroupId)
    ).map { it.transactions }

    fun getAllTransactions(
        tagId: Long? = null,
        recurringId: Long? = null,
        plaidAccountId: Long? = null,
        categoryId: Long? = null,
        assetId: Long? = null,
        groupId: Long? = null,
        isGroup: Boolean? = null,
        status: LunchmoneyTransactionStatus? = null,
        offset: Long? = null,
        limit: Long? = null,
        startDate: LocalDate? = null,
        endDate: LocalDate? = null,
        debitAsNegative: Boolean? = null,
        pending: Boolean? = null
    ): Mono<List<LunchmoneyTransaction>> = execute(
        GetAllTransactionsRequest(
            GetAllTransactionsParams(
                tagId = tagId,
                recurringId = recurringId,
                plaidAccountId = plaidAccountId,
                categoryId = categoryId,
                assetId = assetId,
                groupId = groupId,
                isGroup = isGroup,
                status = status,
                offset = offset,
                limit = limit,
                startDate = startDate,
                endDate = endDate,
                debitAsNegative = debitAsNegative,
                pending = pending
            )
        )
    ).map { it.transactions }

    fun getSingleTransaction(
        transactionId: Long,
        debitAsNegative: Boolean? = null
    ): Mono<LunchmoneyTransaction> = execute(
        GetSingleTransactionRequest(
            transactionId,
            GetSingleTransactionParams(
                debitAsNegative = debitAsNegative
            )
        )
    )

    fun insertTransactions(
        transactions: List<LunchmoneyInsertTransaction>,
        applyRules: Boolean? = null,
        skipDuplicates: Boolean? = null,
        checkForRecurring: Boolean? = null,
        debitAsNegative: Boolean? = null,
        skipBalanceUpdate: Boolean? = null
    ): Mono<List<Long>> = execute(
        InsertTransactionsRequest(
            InsertTransactionRequestParams(
                transactions = transactions,
                applyRules = applyRules,
                skipDuplicates = skipDuplicates,
                checkForRecurring = checkForRecurring,
                debitAsNegative = debitAsNegative,
                skipBalanceUpdate = skipBalanceUpdate
            )
        )
    ).map { it.ids }

    fun unsplitTransactions(
        parentIds: List<Long>,
        removeParents: Boolean? = null
    ): Mono<List<Long>> = execute(
        UnsplitTransactionsRequest(
            UnsplitTransactionsParams(
                parentIds = parentIds,
                removeParents = removeParents
            )
        )
    )

    fun updateTransaction(
        transactionId: Long,
        transaction: LunchmoneyUpdateTransaction,
        split: LunchmoneyTransactionSplit? = null,
        debitAsNegative: Boolean? = null,
        skipBalanceUpdate: Boolean? = null
    ): Mono<LunchmoneyUpdateTransactionResponse> = execute(
        UpdateTransactionRequest(
            transactionId,
            UpdateTransactionParams(
                transaction = transaction,
                split = split,
                debitAsNegative = debitAsNegative,
                skipBalanceUpdate = skipBalanceUpdate
            )
        )
    )

    fun getCurrentUser(): Mono<LunchmoneyUser> = execute(
        GetCurrentUserRequest()
    )

    companion object {
        const val LUNCHMONEY_DEV_BASE_URL = "https://dev.lunchmoney.app/v1"
    }
}
