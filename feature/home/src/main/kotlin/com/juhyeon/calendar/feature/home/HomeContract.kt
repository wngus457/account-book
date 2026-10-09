package com.juhyeon.calendar.feature.home

import com.juhyeon.calendar.feature.home.data.HomeUiModel
import com.juhyeon.calendar.shared.navigation.AddAccount
import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState
import java.time.LocalDate

interface HomeContract {
    sealed interface Event : UiEvent {
        data object OnResume : Event
        data class OnSelectDate(val param: LocalDate) : Event
        data object OnAddAccountClick : Event
        data class OnReceiptClick(val item: HomeUiModel.ExpenseItem) : Event
        data object OnPrevWindow : Event
        data object OnNextWindow : Event
    }

    data class State(
        val uiState: HomeUiState
    ) : UiState {
        sealed interface HomeUiState {
            data object Loading : HomeUiState
            data class Success(
                val expenseList: List<HomeUiModel>,
                val monthlyTotalEarning: Long = 0L,
                val monthlyTotalExpense: Long = 0L
            ) : HomeUiState
        }
    }

    sealed interface Effect : UiEffect {
        data class NavigateToAddAccount(val route: AddAccount) : Effect
    }
}