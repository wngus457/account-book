package com.juhyeon.calendar.domain.setting.baseday

import com.juhyeon.calendar.domain.FlowNoParamUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.setting.SettingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBaseDayUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowNoParamUseCase<Int>(dispatcher) {

    override fun execute(): Flow<Result<Int>> {
        return settingRepository.getBaseDay()
    }
}