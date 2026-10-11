package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.theme.Blue600
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius14
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold

@Composable
internal fun BottomButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = { }
) {
    val (backgroundColor, textColor) = if (enabled) Blue600 to White100 else Color(0xFFEDEDED) to Color(0xFFC8C8C8)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White100)
            .padding(start = 20.dp, top = 13.dp, bottom = 20.dp, end = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(Radius14)
                .background(backgroundColor)
                .clickableSingle(enabled = enabled) { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "추가",
                color = textColor,
                style = MaterialTheme.typography.bold(16)
            )
        }
    }
}

@Preview
@Composable
private fun BottomButtonPreview() {
    BottomButton()
}