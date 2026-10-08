package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.androidds.ui.theme.Radius8
import com.juhyeon.calendar.shared.ui.common.util.preview.VerticalPreviews
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold
import com.juhyeon.calendar.shared.ui.system.theme.theme.medium

@Composable
internal fun NumberKeyComponent(
    onKeyClick: (String) -> Unit
) {
    val keyList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "0", "<-")

    // 바깥 verticalScroll 안에 있으므로 Lazy 대신 일반 Row로 3칸씩 배치
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        keyList.chunked(3).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { key ->
                    NumberComponent(
                        modifier = Modifier.weight(1f),
                        number = key,
                        onClick = { onKeyClick(key) }
                    )
                }
            }
        }
    }
}
@Composable
internal fun NumberComponent(
    modifier: Modifier = Modifier,
    number: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(Radius8)
            .clickable { onClick() }
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = Color(0xFF2D2D2D),
            style = MaterialTheme.typography.bold(20)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberKeyComponentPreview() {
    NumberKeyComponent(
        onKeyClick = { }
    )
}

@VerticalPreviews
@Composable
private fun NumberKeyComponentPreviewVertical() {
    NumberKeyComponent {  }
}