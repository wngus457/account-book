package com.juhyeon.calendar.feature.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import com.juhyeon.calendar.shared.domain.expense.Expense
import com.juhyeon.calendar.shared.domain.expense.insert.InsertExpenseUseCase
import com.juhyeon.calendar.shared.domain.expense.month.GetMonthExpenseListUseCase
import com.juhyeon.calendar.shared.domain.expense.month.GetMonthExpenseParam
import com.juhyeon.calendar.shared.domain.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.take
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertExpenseUseCase: InsertExpenseUseCase,
    private val getMonthExpenseListUseCase: GetMonthExpenseListUseCase
) : ViewModel() {

    private val reducer = MviReducer<HomeContract.Event, HomeContract.State, HomeContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    val localDate = mutableStateOf(LocalDate.now())
    val selectDate = mutableStateOf(LocalDate.now())

    private fun initState() = HomeContract.State

    private fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnSelectDate -> onSelectDate(event.param)
            is HomeContract.Event.OnAddAccountClick -> dateSeparation(selectDate.value)
        }
    }

    private fun dateSeparation(localDate: LocalDate) {
        val year = localDate.year.toString()
        val month = localDate.month.toString()
        val date = localDate.dayOfMonth.toString()
        reducer.setEffect(HomeContract.Effect.NavigateToAddAccount(year = year, month = month, date = date))
    }

    private fun onSelectDate(localDate: LocalDate) {
        val param = Expense(
            year = localDate.year.toString(),
            month = localDate.month.toString(),
            date = localDate.dayOfWeek.value.toString(),
            expenseList = listOf(
                Expense.ExpenseItem(
                    price = 1000,
                    time = "",
                    category = "카테고리",
                    memo = "테스트",
                    isExpenditure = true
                )
            ),
            totalEarning = 1000,
            totalExpense = 0
        )
        insertExpenseUseCase(param)
            .take(1)
            .onSuccess {
                Log.e("테스트", "테스트")
                getMonthExpenseListUseCase(GetMonthExpenseParam(year = localDate.year.toString(), month = localDate.month.toString()))
                    .take(1)
                    .onSuccess {
                        Log.e("테스트", it.toString())
                    }
                    .launchIn(viewModelScope)
            }
            .launchIn(viewModelScope)
    }
}