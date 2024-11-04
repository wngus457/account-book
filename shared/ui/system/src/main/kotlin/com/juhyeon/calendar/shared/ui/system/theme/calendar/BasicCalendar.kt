package com.juhyeon.calendar.shared.ui.system.theme.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.Normal18
import com.juhyeon.calendar.shared.ui.system.theme.Normal8
import com.juhyeon.calendar.shared.ui.system.theme.SemiBold18
import com.juhyeon.calendar.shared.ui.system.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonArrowBack
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonArrowForward
import java.time.LocalDate

@Composable
fun CalendarBasic(
    baseDate: LocalDate,
    selectedDate: LocalDate,
    firstDayOfWeek: Int,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    onSelectedDate: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MonthHeadComponent(
            monthHeadText = "${baseDate.year}년 ${baseDate.monthValue}월",
            onPrevMonthClick = { onPrevMonthClick() },
            onNextMonthClick = { onNextMonthClick() }
        )
        DayOfWeekStandardComponent(
            modifier = Modifier.padding(top = 24.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1..firstDayOfWeek) {
                item {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
            items(baseDate.lengthOfMonth()) { day ->
                val date = baseDate.withDayOfMonth(day + 1)
                val isSelected = remember(baseDate, selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    CalendarDay(
                        modifier = Modifier.padding(top = 10.dp),
                        date = date,
                        isSelected = isSelected,
                        textColor = date.dayOfWeek.toCalendarDayOfWeek().color,
                        onSelectedDate = { onSelectedDate(it) }
                    )
                    Text(
                        text = "1,000",
                        style = MaterialTheme.typography.Normal8,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean,
    textColor: Color,
    onSelectedDate: (LocalDate) -> Unit
) {
    val background = if (isSelected) {
        Modifier.background(Gray400)
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .size(40.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .then(background)
            .clickableSingle { onSelectedDate(date) },
        contentAlignment = Alignment.Center
    ) {
        val currentColor = if (isSelected) White100 else textColor
        Text(
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.Normal18,
            color = currentColor
        )
    }
}

@Composable
private fun MonthHeadComponent(
    monthHeadText: String,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .border(width = 1.dp, color = Gray400, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .background(White100)
                .clickableSingle { onPrevMonthClick() }
                .padding(8.dp),
            imageVector = CommonArrowBack,
            contentDescription = ""
        )
        Text(
            text = monthHeadText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.SemiBold18
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .border(width = 1.dp, color = Gray400, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .background(White100)
                .clickableSingle { onNextMonthClick() }
                .padding(8.dp),
            imageVector = CommonArrowForward,
            contentDescription = ""
        )
    }
}

@Composable
private fun DayOfWeekStandardComponent(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        CalendarDayOfWeek.entries.forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = dayOfWeek.title,
                style = MaterialTheme.typography.Normal18,
                color = dayOfWeek.color,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CalendarViewPreview() {
    val localDate = remember { mutableStateOf(LocalDate.now()) }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    CalendarBasic(
        baseDate = localDate.value,
        selectedDate = selectedDate.value,
        firstDayOfWeek = localDate.value.withDayOfMonth(1).dayOfWeek.toCalendarDayOfWeek().ordinal,
        onPrevMonthClick = { localDate.value = localDate.value.minusMonths(1) },
        onNextMonthClick = { localDate.value = localDate.value.plusMonths(1) },
        onSelectedDate = { selectedDate.value = it }
    )
}