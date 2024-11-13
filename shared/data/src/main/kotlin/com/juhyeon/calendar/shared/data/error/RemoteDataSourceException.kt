package com.juhyeon.calendar.shared.data.error

open class RemoteDataSourceException(
    val errorCode: String,
    val errorMessage: String
) : Exception("[$errorCode] $errorMessage")