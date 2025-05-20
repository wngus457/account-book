package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.medium

@Composable
internal fun NumberKeyComponent(
    onKeyClick: (String) -> Unit
) {
    val keyList = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "0", "<-")

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = false
    ) {
        items(keyList) { key ->
            NumberComponent(
                number = key,
                onClick = { onKeyClick(key) }
            )
        }
    }
}
@Composable
internal fun NumberComponent(
    number: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Radius10)
            .clickable { onClick() }
            .background(Gray400)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.medium(24)
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