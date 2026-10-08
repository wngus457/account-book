package com.juhyeon.calendar.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.androidds.ui.icon.basic.BasicIcon
import com.juhyeon.androidds.ui.icon.basic.BasicIconSize
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.feature.home.data.HomeUiModel
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.icon.categoryIcon
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.departureNormal
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat

@Composable
internal fun ReceiptItem(
    expenseItem: HomeUiModel.ExpenseItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableSingle {  }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(Radius10)
                .background(Color(0xFFF6F6F6)),
            contentAlignment = Alignment.Center
        ) {
            BasicIcon(
                drawableRes = categoryIcon(expenseItem.category?.icon.orEmpty()),
                iconSize = BasicIconSize.Small,
                tint = Color(0xFF7A7A7A)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(
                text = expenseItem.category?.name ?: "미분류",
                style = MaterialTheme.typography.departureNormal(14),
                color = Gray800
            )
            Text(
                text = expenseItem.memo,
                style = MaterialTheme.typography.departureNormal(14),
                color = Gray800
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = expenseItem.price.applyCommaFormat(),
            maxLines = 1,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.departureNormal(14),
            color = Gray800
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceiptItemPreview() {
    Column {
        ReceiptItem(
            HomeUiModel.ExpenseItem(
                price = 1000L,
                time = "2025-01-01",
                memo = "메모",
                isExpenditure = true,
                category = Category(categoryKey = "1", name = "식비", icon = "meal")
            )
        )
        ReceiptItem(
            HomeUiModel.ExpenseItem(
                price = 1000L,
                time = "2025-01-01",
                memo = "메모",
                isExpenditure = true,
                category = Category(categoryKey = "1", name = "식비", icon = "meal")
            )
        )
    }
}