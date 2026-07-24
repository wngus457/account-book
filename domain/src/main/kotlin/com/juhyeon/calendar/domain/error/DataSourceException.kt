package com.juhyeon.calendar.domain.error

data class DataSourceException(
    val errorCode: String,
    val errorMessage: String
) : Exception("[$errorCode] $errorMessage")