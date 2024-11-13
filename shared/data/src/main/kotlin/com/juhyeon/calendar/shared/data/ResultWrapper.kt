package com.juhyeon.calendar.shared.data

import com.juhyeon.calendar.shared.domain.Result

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(
        val message: String,
        val exception: ExceptionResult?
    ) : ResultWrapper<Nothing>() {
        data class ExceptionResult(
            val alertType: String?,
            val confirmUrl: String?,
            val cancelUrl: String?,
            val extras: Extras?,
            val message: String,
            val localizedMessage: String?,
            val code: String,
            val suppressed: List<String>?
        ) {
            data class Extras(
                val extra: String?
            )
        }
    }
    data object NetworkError : ResultWrapper<Nothing>()
}

internal fun ResultWrapper.GenericError.ExceptionResult.toDomain() = Result.Error.ExceptionResult(
    message = message,
    code = code,
)