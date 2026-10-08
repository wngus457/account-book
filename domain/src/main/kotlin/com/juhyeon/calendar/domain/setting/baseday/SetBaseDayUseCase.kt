package com.juhyeon.calendar.domain.setting.baseday

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.setting.SettingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetBaseDayUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, Unit>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<Unit>> {
        return settingRepository.setBaseDay(parameters.coerceIn(MIN_BASE_DAY, MAX_BASE_DAY))
    }
}