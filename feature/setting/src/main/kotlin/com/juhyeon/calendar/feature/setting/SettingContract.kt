package com.juhyeon.calendar.feature.setting

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState

interface SettingContract {

    sealed interface Event : UiEvent {
        data class OnChangeBaseDay(val day: Int) : Event
    }

    data object State : UiState

    sealed interface Effect : UiEffect {

    }
}
