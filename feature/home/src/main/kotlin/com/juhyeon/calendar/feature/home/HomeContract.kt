package com.juhyeon.calendar.feature.home

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState
import java.time.LocalDate

interface HomeContract {
    sealed interface Event : UiEvent {
        data class OnSelectDate(val param: LocalDate): Event
    }

    data object State : UiState

    sealed interface Effect : UiEffect
}