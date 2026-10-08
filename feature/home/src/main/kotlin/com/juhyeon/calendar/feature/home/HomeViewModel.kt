package com.juhyeon.calendar.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.get.GetCategoryListUseCase
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.month.GetMonthExpenseListUseCase
import com.juhyeon.calendar.domain.expense.month.GetMonthExpenseParam
import com.juhyeon.calendar.domain.expense.toLocalDate
import com.juhyeon.calendar.domain.onSuccess
import com.juhyeon.calendar.domain.setting.baseday.DEFAULT_BASE_DAY
import com.juhyeon.calendar.domain.setting.baseday.GetBaseDayUseCase
import com.juhyeon.calendar.domain.setting.baseday.SetBaseDayUseCase
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowEnd
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowStart
import com.juhyeon.calendar.domain.successOr
import com.juhyeon.calendar.feature.home.data.HomeUiModel
import com.juhyeon.calendar.feature.home.data.toUiModel
import com.juhyeon.calendar.shared.navigation.AddAccount
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMonthExpenseListUseCase: GetMonthExpenseListUseCase,
    private val getBaseDayUseCase: GetBaseDayUseCase,
    private val setBaseDayUseCase: SetBaseDayUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private val reducer = MviReducer<HomeContract.Event, HomeContract.State, HomeContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    val baseDay = mutableStateOf(DEFAULT_BASE_DAY)

    /** 화면에 그려지는 구간의 시작일. 기준일이 10이면 10월 10일. */
    val windowStart = mutableStateOf(LocalDate.now().baseDayWindowStart(DEFAULT_BASE_DAY))
    val selectDate = mutableStateOf(LocalDate.now())

    init {
        // 기준일 Flow는 계속 구독 상태라 모달에서 값을 바꾸면 여기로 다시 흘러와 윈도우가 재계산된다.
        getBaseDayUseCase()
            .onSuccess { day ->
                baseDay.value = day
                windowStart.value = selectDate.value.baseDayWindowStart(day)
                refresher()
            }
            .launchIn(viewModelScope)
    }

    private fun initState() = HomeContract.State(
        uiState = HomeContract.State.HomeUiState.Loading
    )

    private fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnResume -> refresher()
            is HomeContract.Event.OnSelectDate -> onSelectDate(event.param)
            is HomeContract.Event.OnAddAccountClick -> reducer.setEffect(HomeContract.Effect.NavigateToAddAccount(addAccountRoute()))
            is HomeContract.Event.OnReceiptClick -> onReceiptClick(event.item)
            is HomeContract.Event.OnPrevWindow -> moveWindow(windowStart.value.minusDays(1).baseDayWindowStart(baseDay.value))
            is HomeContract.Event.OnNextWindow -> moveWindow(windowEnd().plusDays(1))
            is HomeContract.Event.OnChangeBaseDay -> setBaseDayUseCase(event.day).launchIn(viewModelScope)
        }
    }

    private fun windowEnd(): LocalDate = windowStart.value.baseDayWindowEnd(baseDay.value)

    private fun moveWindow(start: LocalDate) {
        windowStart.value = start
        refresher()
    }

    private fun monthExpenses(date: LocalDate) =
        getMonthExpenseListUseCase(
            GetMonthExpenseParam(
                year = date.year.toString(),
                month = date.month.value.toString()
            )
        )
            .take(1)
            .map { it.successOr(listOf<Expense>()) }

    private fun categoryMap() =
        getCategoryListUseCase()
            .take(1)
            .map { result -> result.successOr(listOf<Category>()).associateBy { it.categoryKey } }

    private fun refresher() {
        val start = windowStart.value
        val end = windowEnd()

        // 윈도우는 항상 달력상 한두 달에 걸치므로 시작/종료가 속한 달만 읽어 합친다.
        // 기준일이 1이면 두 조회가 같은 달이라 distinctBy 가 중복을 걷어낸다.
        combine(monthExpenses(start), monthExpenses(end), categoryMap()) { startMonth, endMonth, categoryMap ->
            (startMonth + endMonth)
                .distinctBy { it.key }
                .filter { it.toLocalDate() in start..end }
                .map { it.toUiModel(categoryMap) }
        }
            .onEach { expenseList ->
                reducer.setState {
                    copy(
                        uiState = HomeContract.State.HomeUiState.Success(
                            expenseList = expenseList,
                            monthlyTotalEarning = expenseList.sumOf { it.expense.totalEarning },
                            monthlyTotalExpense = expenseList.sumOf { it.expense.totalExpense }
                        )
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun addAccountRoute(): AddAccount {
        val localDate = selectDate.value
        return AddAccount(
            year = localDate.year.toString(),
            month = localDate.month.value.toString(),
            date = localDate.dayOfMonth.toString()
        )
    }

    private fun onReceiptClick(item: HomeUiModel.ExpenseItem) {
        val route = addAccountRoute().copy(
            expenseKey = item.key,
            price = item.price,
            time = item.time,
            memo = item.memo,
            categoryKey = item.category?.categoryKey ?: "0",
            isExpenditure = item.isExpenditure
        )
        reducer.setEffect(HomeContract.Effect.NavigateToAddAccount(route))
    }

    private fun onSelectDate(date: LocalDate) {
        selectDate.value = date
        // 윈도우 안의 다음 달 날짜를 눌렀다고 화면이 넘어가면 안 되므로 구간 밖일 때만 이동한다.
        if (date !in windowStart.value..windowEnd()) {
            moveWindow(date.baseDayWindowStart(baseDay.value))
        }
    }
}