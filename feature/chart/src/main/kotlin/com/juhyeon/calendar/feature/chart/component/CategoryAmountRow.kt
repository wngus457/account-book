package com.juhyeon.calendar.feature.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.feature.chart.ChartContract
import com.juhyeon.calendar.shared.ui.system.theme.icon.categoryIcon
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray50
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.RadiusFull
import com.juhyeon.calendar.shared.ui.system.theme.theme.departureNormal
import com.juhyeon.calendar.shared.ui.system.theme.theme.medium
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat
import kotlin.math.roundToInt

@Composable
internal fun CategoryAmountRow(
    item: ChartContract.CategoryAmount,
    total: Long
) {
    val ratio = if (total > 0) item.amount.toFloat() / total else 0f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(item.color.copy(alpha = 0.13f), Radius10),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(categoryIcon(item.category?.icon.orEmpty())),
                contentDescription = null,
                tint = item.color
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = item.category?.name ?: "미분류",
                    style = MaterialTheme.typography.medium(14),
                    color = Gray800
                )
                Text(
                    text = "${item.amount.applyCommaFormat()}원",
                    style = MaterialTheme.typography.departureNormal(13),
                    color = Gray800
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Gray50, RadiusFull)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(ratio)
                        .fillMaxHeight()
                        .background(item.color, RadiusFull)
                )
            }
        }
        Text(
            modifier = Modifier.width(34.dp),
            text = "${(ratio * 100).roundToInt()}%",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.normal(11),
            color = Gray400
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryAmountRowPreview() {
    CategoryAmountRow(
        item = ChartContract.CategoryAmount(Category("1", "식비", "meal"), 312000, Color(0xFFFF6B6B)),
        total = 752000
    )
}