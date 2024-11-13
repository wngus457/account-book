package com.juhyeon.calendar.shared.domain.error

data class DataSourceException(
    val errorCode: String,
    val errorMessage: String
) : Exception("[$errorCode] $errorMessage")