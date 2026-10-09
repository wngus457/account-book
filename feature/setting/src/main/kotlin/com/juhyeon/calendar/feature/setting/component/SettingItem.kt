package com.juhyeon.calendar.feature.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.medium
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold

@Composable
internal fun SettingItem(
    title: String,
    itemName: String,
    item: @Composable () -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.medium(12),
            color = Gray400
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = itemName,
                style = MaterialTheme.typography.semiBold(14)
            )
            item()
        }
    }
}

@Preview
@Composable
private fun SettingItemPreview() {
    SettingItem(
        title = "테스트",
        itemName = "테스트1"
    )
}