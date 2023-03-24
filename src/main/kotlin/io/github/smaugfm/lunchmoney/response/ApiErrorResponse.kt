package io.github.smaugfm.lunchmoney.response

import io.github.smaugfm.lunchmoney.exception.LunchmoneyApiResponseException
import io.github.smaugfm.lunchmoney.model.LunchmoneyCategoryDeletionDependency
import io.github.smaugfm.lunchmoney.serializer.StringOrStringArrayDeserializer
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    var name: String? = null,
    var message: String? = null,
    @Serializable(with = StringOrStringArrayDeserializer::class)
    var error: List<String>? = null,
    var dependents: LunchmoneyCategoryDeletionDependency? = null
) {
    fun toException(body: String, statusCode: Int): LunchmoneyApiResponseException {
        return LunchmoneyApiResponseException(this, body, statusCode)
    }
}
