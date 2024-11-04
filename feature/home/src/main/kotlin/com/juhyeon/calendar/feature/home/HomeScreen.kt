package com.juhyeon.calendar.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.shared.ui.system.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.calendar.CalendarBasic
import com.juhyeon.calendar.shared.ui.system.theme.calendar.toCalendarDayOfWeek
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
                else -> {}
            }
        }
    }
    HomeContents()
}

@Composable
private fun HomeContents(

) {
    val localDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxSize()
        ) {
            CalendarBasic(
                baseDate = localDate.value,
                selectedDate = selectedDate.value,
                firstDayOfWeek = localDate.value.withDayOfMonth(1).dayOfWeek.toCalendarDayOfWeek().ordinal,
                onPrevMonthClick = { localDate.value = localDate.value.minusMonths(1) },
                onNextMonthClick = { localDate.value = localDate.value.plusMonths(1) },
                onSelectedDate = { selectedDate.value = it }
            )
        }
    }
}

@Preview
@Composable
private fun HomeContentsPreview() {
    HomeContents()
}