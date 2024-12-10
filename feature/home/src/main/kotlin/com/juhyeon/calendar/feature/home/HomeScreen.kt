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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.shared.navigation.AddAccount
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.Departure16
import com.juhyeon.calendar.shared.ui.system.theme.Departure18
import com.juhyeon.calendar.shared.ui.system.theme.Medium16
import com.juhyeon.calendar.shared.ui.system.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.calendar.CalendarBasic
import com.juhyeon.calendar.shared.ui.system.theme.calendar.toCalendarDayOfWeek
import com.juhyeon.calendar.shared.ui.system.theme.canvas.DashedLine
import java.time.LocalDate

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.stateFlow.collectAsState().value
    val postEvent = homeViewModel.eventHandler

    LaunchedEffect(true) {
        homeViewModel.effectFlow.collect { effect ->
            when (effect) {
                is HomeContract.Effect.NavigateToAddAccount -> navController.navigate(AddAccount(effect.year, effect.month, effect.date))
            }
        }
    }
    HomeContents(
        localDate = homeViewModel.localDate.value,
        selectDate = homeViewModel.selectDate.value,
        onSelectDate = {
            homeViewModel.selectDate.value = it
            postEvent(HomeContract.Event.OnSelectDate(it))
        },
        onAddAccountClick = { postEvent(HomeContract.Event.OnAddAccountClick) },
        onChangeMonth = { homeViewModel.localDate.value = it }
    )
}

@Composable
private fun HomeContents(
    localDate: LocalDate,
    selectDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit,
    onAddAccountClick: () -> Unit,
    onChangeMonth: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CalendarBasic(
                baseDate = localDate,
                selectedDate = selectDate,
                firstDayOfWeek = localDate.withDayOfMonth(1).dayOfWeek.toCalendarDayOfWeek().ordinal,
                onPrevMonthClick = { onChangeMonth(localDate.minusMonths(1)) },
                onNextMonthClick = { onChangeMonth(localDate.plusMonths(1)) },
                onSelectedDate = { onSelectDate(it) }
            )
            HorizontalDivider()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickableSingleIgnoreInteraction { onAddAccountClick() },
                textAlign = TextAlign.Center,
                text = "+ 등록",
                style = MaterialTheme.typography.Departure16
            )
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(10) {
                    ReceiptItem()
                }
                item {
                    DashedLine(modifier = Modifier.padding(vertical = 6.dp))
                }
                item {
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
                                style = MaterialTheme.typography.Departure16
                            )

                            Text(
                                text = "+1,000",
                                style = MaterialTheme.typography.Departure16
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "지출",
                                style = MaterialTheme.typography.Departure16
                            )

                            Text(
                                text = "-1,000",
                                style = MaterialTheme.typography.Departure16
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "합계",
                                style = MaterialTheme.typography.Departure16
                            )

                            Text(
                                text = "+1,000",
                                style = MaterialTheme.typography.Departure16
                            )
                        }
                    }
                }
            }
            Column {
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
                        style = MaterialTheme.typography.Departure18
                    )

                    Text(
                        text = "+1,000",
                        style = MaterialTheme.typography.Departure18
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeContentsPreview() {
    HomeContents(
        localDate = LocalDate.now(),
        selectDate = LocalDate.now(),
        onSelectDate = { },
        onAddAccountClick = { },
        onChangeMonth = { }
    )
}