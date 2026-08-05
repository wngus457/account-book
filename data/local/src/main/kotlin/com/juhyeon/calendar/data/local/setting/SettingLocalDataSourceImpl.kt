package com.juhyeon.calendar.data.local.setting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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

    companion object {
        const val APP_KEY = "is_first_app_start"
    }
}