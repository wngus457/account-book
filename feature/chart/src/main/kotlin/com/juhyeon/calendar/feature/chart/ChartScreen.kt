package com.juhyeon.calendar.feature.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.feature.chart.component.AccountButtonComponent
import com.juhyeon.calendar.feature.chart.component.CategoryAmountRow
import com.juhyeon.calendar.feature.chart.component.ChartCard
import com.juhyeon.calendar.feature.chart.component.ChartMonthChange
import com.juhyeon.calendar.shared.ui.common.util.OnLifecycleEvent
import com.juhyeon.calendar.shared.ui.common.util.preview.VerticalPreview
import com.juhyeon.calendar.shared.ui.system.theme.theme.Black900
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray50
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold
import java.time.LocalDate

@Composable
fun ChartRoute(
    navController: NavHostController,
    viewModel: ChartViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val postEvent = viewModel.eventHandler

    // 내역을 추가하고 돌아왔을 때 최신 합계를 보여주기 위해 화면에 들어올 때마다 다시 읽는다.
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> postEvent(ChartContract.Event.OnResume)
            else -> { }
        }
    }

    LaunchedEffect(true) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                else -> { }
            }
        }
    }

    ChartScreen(
        state = state,
        onPrevWindow = { postEvent(ChartContract.Event.OnPrevWindow) },
        onNextWindow = { postEvent(ChartContract.Event.OnNextWindow) },
        onExpenditureChange = { postEvent(ChartContract.Event.OnExpenditureChange(it)) },
        onPieModeChange = { postEvent(ChartContract.Event.OnPieModeChange(it)) }
    )
}

@Composable
private fun ChartScreen(
    state: ChartContract.State,
    onPrevWindow: () -> Unit = { },
    onNextWindow: () -> Unit = { },
    onExpenditureChange: (Boolean) -> Unit = { },
    onPieModeChange: (Boolean) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 14.dp),
                text = "차트 분석",
                style = MaterialTheme.typography.semiBold(19),
                color = Black900
            )
            ChartMonthChange(
                monthHeadText = "${state.windowStart.year}년 ${state.windowStart.monthValue}월",
                onPrevMonthClick = onPrevWindow,
                onNextMonthClick = onNextWindow
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                AccountButtonComponent(
                    isExpenditure = state.isExpenditure,
                    onChangeExpenditure = onExpenditureChange
                )
            }
            item {
                ChartCard(
                    isExpenditure = state.isExpenditure,
                    isPieMode = state.isPieMode,
                    total = state.total,
                    windowStart = state.windowStart,
                    dailyAmounts = state.dailyAmounts,
                    categoryAmounts = state.categoryAmounts,
                    onPieModeChange = onPieModeChange
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "카테고리별 비중",
                    style = MaterialTheme.typography.semiBold(13),
                    color = Gray800
                )
            }
            item {
                Column {
                    state.categoryAmounts.forEachIndexed { index, item ->
                        if (index > 0) HorizontalDivider(color = Gray50)
                        CategoryAmountRow(item = item, total = state.total)
                    }
                }
            }
        }
    }
}

@VerticalPreview
@Composable
private fun ChartScreenPreview() {
    ChartScreen(
        state = ChartContract.State(
            windowStart = LocalDate.of(2026, 10, 1),
            dailyAmounts = List(31) { (10000 + (it * 7919 % 30000)).toLong() },
            categoryAmounts = listOf(
                ChartContract.CategoryAmount(Category("1", "식비", "meal"), 312000, Color(0xFFFF6B6B)),
                ChartContract.CategoryAmount(Category("2", "쇼핑", "cart"), 158000, Color(0xFFFFA94D)),
                ChartContract.CategoryAmount(Category("3", "교통비", "car"), 96000, Color(0xFF4D96FF)),
                ChartContract.CategoryAmount(Category("4", "여행", "airplane"), 0, Color(0xFF63E6BE))
            )
        )
    )
}