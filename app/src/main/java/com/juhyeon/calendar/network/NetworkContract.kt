package com.juhyeon.calendar.network

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState

interface NetworkContract {

    sealed interface Event : UiEvent

    data class State(
        val networkState: NetworkState
    ) : UiState {
        sealed interface NetworkState {
            data object None : NetworkState
            data object Connected : NetworkState
            data object DisConnected : NetworkState
        }
    }

    sealed interface Effect : UiEffect
}