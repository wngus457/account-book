package com.juhyeon.calendar.domain.setting.app

import com.juhyeon.calendar.domain.FlowNoParamUseCase
import com.juhyeon.calendar.domain.Result
import com.juhyeon.calendar.domain.annotaion.DefaultDispatcher
import com.juhyeon.calendar.domain.setting.SettingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFirstAppStartUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @param:DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowNoParamUseCase<Boolean>(dispatcher) {

    override fun execute(): Flow<Result<Boolean>> {
        return settingRepository.getFirstAppStart()
    }
}