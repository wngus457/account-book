package com.juhyeon.calendar.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.expense.Expense
import com.juhyeon.calendar.domain.expense.toLocalDate
import com.juhyeon.calendar.feature.home.data.HomeUiModel
import com.juhyeon.calendar.feature.home.data.toUiModel
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowEnd
import com.juhyeon.calendar.domain.setting.baseday.baseDayWindowStart
import com.juhyeon.calendar.feature.home.component.BaseDayModal
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.common.util.OnLifecycleEvent
import com.juhyeon.calendar.shared.ui.system.theme.calendar.CalendarBasic
import com.juhyeon.calendar.shared.ui.system.theme.calendar.toCalendarDayOfWeek
import com.juhyeon.calendar.shared.ui.system.theme.canvas.DashedLine
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.departureNormal
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat
import java.time.LocalDate

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.stateFlow.collectAsState().value
    val postEvent = homeViewModel.eventHandler

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> { postEvent(HomeContract.Event.OnResume) }
            else -> { }
        }
    }

    LaunchedEffect(true) {
        homeViewModel.effectFlow.collect { effect ->
            when (effect) {
                is HomeContract.Effect.NavigateToAddAccount -> navController.navigate(effect.route)
            }
        }
    }
    var showBaseDayModal by remember { mutableStateOf(false) }

    HomeContents(
        state = state,
        windowStart = homeViewModel.windowStart.value,
        baseDay = homeViewModel.baseDay.value,
        selectDate = homeViewModel.selectDate.value,
        onSelectDate = { postEvent(HomeContract.Event.OnSelectDate(it)) },
        onAddAccountClick = { postEvent(HomeContract.Event.OnAddAccountClick) },
        onReceiptClick = { postEvent(HomeContract.Event.OnReceiptClick(it)) },
        onPrevWindow = { postEvent(HomeContract.Event.OnPrevWindow) },
        onNextWindow = { postEvent(HomeContract.Event.OnNextWindow) },
        onBaseDayClick = { showBaseDayModal = true }
    )

    BaseDayModal(
        show = showBaseDayModal,
        baseDay = homeViewModel.baseDay.value,
        onConfirm = {
            postEvent(HomeContract.Event.OnChangeBaseDay(it))
            showBaseDayModal = false
        },
        onDismiss = { showBaseDayModal = false }
    )
}

@Composable
private fun HomeContents(
    state: HomeContract.State,
    windowStart: LocalDate,
    baseDay: Int,
    selectDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit,
    onAddAccountClick: () -> Unit,
    onReceiptClick: (HomeUiModel.ExpenseItem) -> Unit = { },
    onPrevWindow: () -> Unit,
    onNextWindow: () -> Unit,
    onBaseDayClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
    ) {
        if (state.uiState is HomeContract.State.HomeUiState.Success) {
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CalendarBasic(
                    expenseList = state.uiState.expenseList.map { it.expense },
                    startDate = windowStart,
                    endDate = windowStart.baseDayWindowEnd(baseDay),
                    selectedDate = selectDate,
                    firstDayOfWeek = windowStart.dayOfWeek.toCalendarDayOfWeek().ordinal,
                    onPrevMonthClick = onPrevWindow,
                    onNextMonthClick = onNextWindow,
                    onTitleClick = onBaseDayClick,
                    onSelectedDate = { onSelectDate(it) }
                )
                HorizontalDivider()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableSingleIgnoreInteraction { onAddAccountClick() },
                    textAlign = TextAlign.Center,
                    text = "+ 등록",
                    style = MaterialTheme.typography.departureNormal(16)
                )
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(state.uiState.expenseList.find { it.expense.toLocalDate() == selectDate }?.expenseList ?: listOf()) {
                        ReceiptItem(
                            expenseItem = it,
                            onClick = { onReceiptClick(it) }
                        )
                    }
                    item {
                        DashedLine(modifier = Modifier.padding(vertical = 6.dp))
                    }
                    item {
                        val selectedExpense = state.uiState.expenseList.find { it.expense.toLocalDate() == selectDate }?.expense
                        val dayEarning = selectedExpense?.totalEarning ?: 0L
                        val dayExpense = selectedExpense?.totalExpense ?: 0L
                        val dayBalance = dayEarning - dayExpense

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "수입",
                                    style = MaterialTheme.typography.departureNormal(16)
                                )

                                Text(
                                    text = "+${dayEarning.applyCommaFormat()}",
                                    style = MaterialTheme.typography.departureNormal(16),
                                    color = Color.Blue
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "지출",
                                    style = MaterialTheme.typography.departureNormal(16)
                                )

                                Text(
                                    text = "-${dayExpense.applyCommaFormat()}",
                                    style = MaterialTheme.typography.departureNormal(16),
                                    color = Color.Red
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "합계",
                                    style = MaterialTheme.typography.departureNormal(16)
                                )

                                Text(
                                    text = "${if (dayBalance >= 0) "+" else ""}${dayBalance.applyCommaFormat()}",
                                    style = MaterialTheme.typography.departureNormal(16),
                                    color = if (dayBalance >= 0) Color.Blue else Color.Red
                                )
                            }
                        }
                    }
                }
                Column {
                    val monthlyBalance = state.uiState.monthlyTotalEarning - state.uiState.monthlyTotalExpense
                    DashedLine(
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "이번달 총 잔액",
                            style = MaterialTheme.typography.departureNormal(18)
                        )

                        Text(
                            text = "${if (monthlyBalance >= 0) "+" else ""}${monthlyBalance.applyCommaFormat()}",
                            style = MaterialTheme.typography.departureNormal(18),
                            color = if (monthlyBalance >= 0) Color.Blue else Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentsPreview() {
    HomeContents(
        state = HomeContract.State(
            uiState = HomeContract.State.HomeUiState.Success(
                expenseList = listOf(
                    Expense(
                        key = "0",
                        year = "2025",
                        month = "1",
                        date = "1",
                        expenseList = listOf(
                            Expense.ExpenseItem(
                                price = 1000L,
                                time = "2025-01-01",
                                category = "1",
                                memo = "메모",
                                isExpenditure = true
                            )
                        ),
                        totalExpense = 1000L,
                        totalEarning = 0L
                    ).toUiModel(mapOf("1" to Category(categoryKey = "1", name = "식비", icon = "meal")))
                ),
                monthlyTotalEarning = 0L,
                monthlyTotalExpense = 1000L
            )
        ),
        windowStart = LocalDate.now().baseDayWindowStart(10),
        baseDay = 10,
        selectDate = LocalDate.now(),
        onSelectDate = { },
        onAddAccountClick = { },
        onPrevWindow = { },
        onNextWindow = { },
        onBaseDayClick = { }
    )
}