package com.juhyeon.calendar.domain.setting.app

import com.juhyeon.calendar.domain.FlowUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.setting.SettingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFirstAppStartUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Boolean, Unit>(dispatcher) {

    override fun execute(parameters: Boolean): Flow<Result<Unit>> {
        return settingRepository.setFirstAppStart(parameters)
    }
}