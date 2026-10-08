package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat

@Composable
internal fun InputAmountComponent(
    price: String = "",
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color(0xFF555555).copy(alpha = 0.16f),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "금액",
            color = Color(0xFF7A7A7A),
            style = MaterialTheme.typography.bold(13)
        )

        Row(
            modifier = Modifier
                .padding(bottom = 17.dp)
                .fillMaxWidth()
                .height(45.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.End)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = if (price.isEmpty()) "0" else price.toLong().applyCommaFormat(),
                textAlign = TextAlign.End,
                color = Color(0xFFCBCBCB),
                style = MaterialTheme.typography.bold(38)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 8.dp),
                text = "원",
                color = Color(0xFF7A7A7A),
                style = MaterialTheme.typography.bold(20)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputAmountComponentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        InputAmountComponent()
    }
}