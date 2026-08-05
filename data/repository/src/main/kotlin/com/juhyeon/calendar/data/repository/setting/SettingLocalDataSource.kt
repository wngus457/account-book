package com.juhyeon.calendar.data.repository.setting

import kotlinx.coroutines.flow.Flow

interface SettingLocalDataSource {
    suspend fun setFirstAppStart(status: Boolean)
    fun getFirstAppStart(): Flow<Boolean>
}