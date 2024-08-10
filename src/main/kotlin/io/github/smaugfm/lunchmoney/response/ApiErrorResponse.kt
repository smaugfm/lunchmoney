package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryDeletionDependency
import io.github.smaugfm.lunchmoney.serializer.CategoryDeletionErrorSerializer
import io.github.smaugfm.lunchmoney.serializer.StructuredApiErrorResponseSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class ApiErrorResponse {

    abstract val msg: String

    data class UnknownApiErrorResponse(override val msg: String) : ApiErrorResponse()

    @Serializable(StructuredApiErrorResponseSerializer::class)
    sealed class StructuredApiErrorResponse : ApiErrorResponse() {
        @Serializable
        data class AccessTokenError(val name: String, val message: String) : StructuredApiErrorResponse() {
            override val msg = message
        }

        @Serializable
        data class SingleError(val error: String) : StructuredApiErrorResponse() {
            override val msg = error
        }

        @Serializable
        data class MultipleErrors(val error: List<String>) : StructuredApiErrorResponse() {
            override val msg = error.joinToString("\n")
        }

        @Serializable(with = CategoryDeletionErrorSerializer::class)
        sealed class CategoryDeletionError : StructuredApiErrorResponse() {

            @Serializable
            data class CategoryDeletionDependencySingle(
                val dependents: LunchmoneyCategoryDeletionDependency
            ) : CategoryDeletionError() {
                override val msg: String
                    get() = json.encodeToString(dependents)
            }

            @Serializable
            data class CategoryDeletionDependencyMultiple(
                val dependents: List<LunchmoneyCategoryDeletionDependency>
            ) : CategoryDeletionError() {
                override val msg: String
                    get() = json.encodeToString(dependents)
            }
        }
    }

    companion object {
        private val json = Json { prettyPrint = true }
    }
}
