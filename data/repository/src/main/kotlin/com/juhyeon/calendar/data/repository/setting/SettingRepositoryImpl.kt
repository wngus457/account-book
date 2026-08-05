package com.juhyeon.calendar.data.repository.setting

import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.mapToResult
import com.juhyeon.calendar.domain.setting.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingLocalDataSource: SettingLocalDataSource
) : SettingRepository {

    override fun setFirstAppStart(status: Boolean): Flow<Result<Unit>> = flow {
        emit(Result.Success(settingLocalDataSource.setFirstAppStart(status)))
    }

    override fun getFirstAppStart(): Flow<Result<Boolean>> = settingLocalDataSource.getFirstAppStart().mapToResult()
}