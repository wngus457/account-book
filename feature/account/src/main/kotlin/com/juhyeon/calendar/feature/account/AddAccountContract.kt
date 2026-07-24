package com.juhyeon.calendar.feature.account

import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState

interface AddAccountContract {

    sealed interface Event : UiEvent {
        data object OnBackClick : Event
        data class OnKeyClick(val key: String) : Event
        data object OnSaveClick : Event
        data class OnMemoChange(val memo: String) : Event
        data class OnCategorySelect(val category: String) : Event
        data class OnExpenditureChange(val isExpenditure: Boolean) : Event
    }

    data class State(
        val price: String = "0",
        val memo: String = "",
        val selectedCategory: String = "0",
        val isExpenditure: Boolean = true,
        val isSaving: Boolean = false
    ) : UiState

    sealed interface Effect : UiEffect {
        data object NavigateToBack : Effect
        data object SaveSuccess : Effect
    }
}