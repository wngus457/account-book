package com.juhyeon.calendar.shared.data.error

import com.juhyeon.calendar.shared.data.ResultWrapper
import com.juhyeon.calendar.shared.data.toDomain
import com.juhyeon.calendar.shared.domain.Result
import com.juhyeon.calendar.shared.domain.error.DataSourceException

internal fun Exception.toDomain() = when (this) {
    is RemoteDataSourceException -> DataSourceException(errorCode, errorMessage)
    else -> this
}

internal fun <T, D> ResultWrapper<T>.toDomain(onMapSuccess: (T) -> D) = when (this) {
    is ResultWrapper.Success -> Result.Success(onMapSuccess(value))
    is ResultWrapper.GenericError -> Result.Error(
        exceptionResult = exception?.toDomain(),
        message = message
    )
    is ResultWrapper.NetworkError -> Result.Error(
        exceptionResult = null,
        message = "Network Error"
    )
}