package com.juhyeon.calendar.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.domain.expense.Expense
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.Departure14
import com.juhyeon.calendar.shared.ui.system.theme.Gray800
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat

@Composable
internal fun ReceiptItem(
    expenseItem: Expense.ExpenseItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableSingle {  }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(
                text = expenseItem.category,
                style = MaterialTheme.typography.Departure14,
                color = Gray800
            )
            Text(
                text = expenseItem.memo,
                style = MaterialTheme.typography.Departure14,
                color = Gray800
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = expenseItem.price.applyCommaFormat(),
            maxLines = 1,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.Departure14,
            color = Gray800
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReceiptItemPreview() {
    Column {
        ReceiptItem(
            Expense.ExpenseItem(
                price = 1000L,
                time = "2025-01-01",
                category = "카테고리",
                memo = "메모",
                isExpenditure = true
            )
        )
        ReceiptItem(
            Expense.ExpenseItem(
                price = 1000L,
                time = "2025-01-01",
                category = "카테고리",
                memo = "메모",
                isExpenditure = true
            )
        )
    }
}