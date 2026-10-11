package com.juhyeon.calendar.feature.chart

import androidx.compose.ui.graphics.Color
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.shared.core.mvi.UiEffect
import com.juhyeon.calendar.shared.core.mvi.UiEvent
import com.juhyeon.calendar.shared.core.mvi.UiState
import java.time.LocalDate

interface ChartContract {

    sealed interface Event : UiEvent {
        data object OnResume : Event
        data object OnPrevWindow : Event
        data object OnNextWindow : Event
        data class OnExpenditureChange(val isExpenditure: Boolean) : Event
        data class OnPieModeChange(val isPieMode: Boolean) : Event
    }

    data class State(
        val windowStart: LocalDate,
        val isExpenditure: Boolean = true,
        val isPieMode: Boolean = false,
        /** 윈도우 시작일부터 하루 단위 합계. */
        val dailyAmounts: List<Long> = emptyList(),
        /** 금액 내림차순. */
        val categoryAmounts: List<CategoryAmount> = emptyList()
    ) : UiState {
        val total: Long get() = categoryAmounts.sumOf { it.amount }
    }

    data class CategoryAmount(
        val category: Category?,
        val amount: Long,
        val color: Color
    )

    sealed interface Effect : UiEffect {

    }
}
