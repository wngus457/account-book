package com.juhyeon.calendar.feature.setting

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.domain.onSuccess
import com.juhyeon.calendar.domain.setting.baseday.DEFAULT_BASE_DAY
import com.juhyeon.calendar.domain.setting.baseday.GetBaseDayUseCase
import com.juhyeon.calendar.domain.setting.baseday.SetBaseDayUseCase
import com.juhyeon.calendar.shared.core.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    getBaseDayUseCase: GetBaseDayUseCase,
    private val setBaseDayUseCase: SetBaseDayUseCase
) : BaseViewModel<SettingContract.Event, SettingContract.State, SettingContract.Effect>() {

    val baseDay = mutableIntStateOf(DEFAULT_BASE_DAY)

    init {
        getBaseDayUseCase()
            .onSuccess { baseDay.intValue = it }
            .launchIn(viewModelScope)
    }

    override fun initState() = SettingContract.State

    override fun handleEvent(event: SettingContract.Event) {
        when (event) {
            is SettingContract.Event.OnChangeBaseDay -> setBaseDayUseCase(event.day).launchIn(viewModelScope)
        }
    }
}