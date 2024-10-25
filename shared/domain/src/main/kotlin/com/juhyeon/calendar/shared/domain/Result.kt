package com.juhyeon.calendar.shared.domain

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val exception: Exception = Exception(),
        val exceptionResult: ExceptionResult? = null,
        val message: String = ""
    ) : Result<Nothing>() {
        data class ExceptionResult(
            val code: String,
            val message: String
        )
    }

    data object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data