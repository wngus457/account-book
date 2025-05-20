package com.juhyeon.calendar.feature.account

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState
import com.juhyeon.calendar.shared.domain.expense.Expense

interface AddAccountContract {

    sealed interface Event : UiEvent {
        data object OnBackClick : Event
        data class OnKeyClick(val key: String) : Event
    }

    data class State(
        val expense: Expense? = null
    ) : UiState

    sealed interface Effect : UiEffect {
        data object NavigateToBack : Effect
    }
}