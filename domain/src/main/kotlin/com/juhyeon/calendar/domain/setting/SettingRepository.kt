package com.juhyeon.calendar.domain.setting

import com.juhyeon.calendar.domain.Result
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun setFirstAppStart(status: Boolean): Flow<Result<Unit>>
    fun getFirstAppStart(): Flow<Result<Boolean>>
    fun setBaseDay(day: Int): Flow<Result<Unit>>
    fun getBaseDay(): Flow<Result<Int>>
}