package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold

@Composable
internal fun AccountButtonComponent(
    isExpenditure: Boolean = true,
    onChangeExpenditure: (Boolean) -> Unit = { }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color(0xFFF6F6F6)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AccountButton(
            text = "지출",
            isSelected = isExpenditure,
            onChangeExpenditure = { onChangeExpenditure(true) }
        )
        AccountButton(
            text = "수입",
            isSelected = !isExpenditure,
            onChangeExpenditure = { onChangeExpenditure(false) }
        )
    }
}

@Composable
private fun RowScope.AccountButton(
    text: String = "",
    isSelected: Boolean = true,
    onChangeExpenditure: () -> Unit = { }
) {
    val (backgroundColor, textColor) = if (isSelected) {
        Color(0xFFF4402C) to Color.White
    } else {
        Color(0xFFF6F6F6) to Color(0xFF7A7A7A)
    }
    Box(
        modifier = Modifier
            .padding(4.dp)
            .weight(1f)
            .height(44.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickableSingleIgnoreInteraction { onChangeExpenditure() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bold(15),
            color = textColor
        )
    }
}


@Preview
@Composable
private fun AccountButtonComponentPreview() {
    AccountButtonComponent()
}