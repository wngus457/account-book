package com.juhyeon.calendar.feature.splash

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState

interface SplashContract {
    sealed interface Event : UiEvent {

    }

    data object State : UiState

    sealed interface Effect : UiEffect {

    }
}