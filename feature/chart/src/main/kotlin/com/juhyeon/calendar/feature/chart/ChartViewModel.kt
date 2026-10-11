package com.juhyeon.calendar.feature.chart

import androidx.compose.ui.graphics.Color
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
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowEnd
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowStart
import com.juhyeon.calendar.domain.successOr
import com.juhyeon.calendar.shared.core.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getMonthExpenseListUseCase: GetMonthExpenseListUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    getBaseDayUseCase: GetBaseDayUseCase
) : BaseViewModel<ChartContract.Event, ChartContract.State, ChartContract.Effect>() {

    private var baseDay = DEFAULT_BASE_DAY
    private var windowStart = LocalDate.now().baseDayWindowStart(DEFAULT_BASE_DAY)
    private var isExpenditure = true
    private var expenses = listOf<Expense>()
    private var categories = listOf<Category>()

    init {
        // 설정 화면에서 기준일을 바꾸면 여기로 다시 흘러와 윈도우가 재계산된다.
        getBaseDayUseCase()
            .onSuccess { day ->
                baseDay = day
                windowStart = windowStart.baseDayWindowStart(day)
                refresher()
            }
            .launchIn(viewModelScope)
    }

    // BaseViewModel 생성자에서 호출되므로 아직 초기화 전인 프로퍼티를 읽으면 안 된다.
    override fun initState() = ChartContract.State(windowStart = LocalDate.now().baseDayWindowStart(DEFAULT_BASE_DAY))

    override fun handleEvent(event: ChartContract.Event) {
        when (event) {
            is ChartContract.Event.OnResume -> refresher()
            is ChartContract.Event.OnPrevWindow -> moveWindow(windowStart.minusDays(1).baseDayWindowStart(baseDay))
            is ChartContract.Event.OnNextWindow -> moveWindow(windowEnd().plusDays(1))
            is ChartContract.Event.OnExpenditureChange -> {
                isExpenditure = event.isExpenditure
                publish()
            }
            is ChartContract.Event.OnPieModeChange -> reducer.setState { copy(isPieMode = event.isPieMode) }
        }
    }

    private fun windowEnd(): LocalDate = windowStart.baseDayWindowEnd(baseDay)

    private fun moveWindow(start: LocalDate) {
        windowStart = start
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

    private fun refresher() {
        val start = windowStart
        val end = windowEnd()

        // 윈도우는 달력상 한두 달에 걸치므로 시작/종료가 속한 달만 읽어 합친다. (HomeViewModel 과 동일)
        combine(
            monthExpenses(start),
            monthExpenses(end),
            getCategoryListUseCase().take(1).map { it.successOr(listOf<Category>()) }
        ) { startMonth, endMonth, categoryList ->
            expenses = (startMonth + endMonth)
                .distinctBy { it.key }
                .filter { it.toLocalDate() in start..end }
            categories = categoryList
        }
            .onEach { publish() }
            .launchIn(viewModelScope)
    }

    private fun publish() {
        val start = windowStart
        val days = ChronoUnit.DAYS.between(start, windowEnd()).toInt() + 1
        val items = expenses.flatMap { expense ->
            val date = expense.toLocalDate()
            expense.expenseList
                .filter { it.isExpenditure == isExpenditure }
                .map { date to it }
        }

        val dailyAmounts = List(days) { offset ->
            val date = start.plusDays(offset.toLong())
            items.filter { it.first == date }.sumOf { it.second.price }
        }

        val amountByKey = items.groupBy({ it.second.category }, { it.second.price }).mapValues { it.value.sum() }
        // 색은 카테고리 목록 순서로 고정해 지출/수입을 오가도 같은 카테고리는 같은 색을 유지한다.
        val known = categories.mapIndexed { index, category ->
            ChartContract.CategoryAmount(
                category = category,
                amount = amountByKey[category.categoryKey] ?: 0L,
                color = CATEGORY_COLORS[index % CATEGORY_COLORS.size]
            )
        }
        val unknownAmount = amountByKey.filterKeys { key -> categories.none { it.categoryKey == key } }.values.sum()
        val unknown = if (unknownAmount > 0) {
            listOf(ChartContract.CategoryAmount(category = null, amount = unknownAmount, color = UNKNOWN_COLOR))
        } else {
            emptyList()
        }

        reducer.setState {
            copy(
                windowStart = start,
                isExpenditure = isExpenditure,
                dailyAmounts = dailyAmounts,
                categoryAmounts = (known + unknown).sortedByDescending { it.amount }
            )
        }
    }

    private companion object {
        val CATEGORY_COLORS = listOf(
            Color(0xFFFF6B6B),
            Color(0xFF4D96FF),
            Color(0xFFFFA94D),
            Color(0xFF63E6BE),
            Color(0xFFB197FC),
            Color(0xFFFFD43B),
            Color(0xFFFF8787),
            Color(0xFF74C0FC)
        )
        val UNKNOWN_COLOR = Color(0xFFADB5BD)
    }
}