package com.juhyeon.calendar.feature.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonArrowBack
import com.juhyeon.calendar.shared.ui.system.theme.icon.CommonArrowForward
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius12
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold

@Composable
internal fun ChartMonthChange(
    monthHeadText: String = "",
    onPrevMonthClick: () -> Unit = { },
    onNextMonthClick: () -> Unit = { }
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
                .size(32.dp)
                .border(width = 1.dp, color = Color(0xFFE4E7EC), shape = Radius12)
                .clip(shape = Radius12)
                .background(White100)
                .clickableSingle { onPrevMonthClick() }
                .padding(6.dp),
            imageVector = CommonArrowBack,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = monthHeadText,
            textAlign = TextAlign.Center,
            color = Color(0xFF1D2939),
            style = MaterialTheme.typography.semiBold(15)
        )
        Icon(
            modifier = Modifier
                .size(32.dp)
                .border(width = 1.dp, color = Color(0xFFE4E7EC), shape = Radius12)
                .clip(shape = Radius12)
                .background(White100)
                .clickableSingle { onNextMonthClick() }
                .padding(6.dp),
            imageVector = CommonArrowForward,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun ChartMonthChangePreview() {
    ChartMonthChange(
        monthHeadText = "2023년 1월"
    )
}