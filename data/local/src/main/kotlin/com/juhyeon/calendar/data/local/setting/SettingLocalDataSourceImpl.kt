package com.juhyeon.calendar.data.local.setting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.juhyeon.calendar.data.repository.setting.SettingLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class SettingLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingLocalDataSource {

    override suspend fun setFirstAppStart(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(APP_KEY)] = status
        }
    }

    override fun getFirstAppStart(): Flow<Boolean> = dataStore.data
        .take(1)
        .map { preferences -> preferences[booleanPreferencesKey(APP_KEY)] ?: true }

    override suspend fun setBaseDay(day: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(BASE_DAY_KEY)] = day
        }
    }

    // take(1) 없음 - 기준일을 바꾸면 캘린더가 바로 다시 그려져야 하므로 계속 구독한다.
    override fun getBaseDay(): Flow<Int> = dataStore.data
        .map { preferences -> preferences[intPreferencesKey(BASE_DAY_KEY)] ?: DEFAULT_BASE_DAY }

    companion object {
        const val APP_KEY = "is_first_app_start"
        const val BASE_DAY_KEY = "calendar_base_day"
        const val DEFAULT_BASE_DAY = 1
    }
}