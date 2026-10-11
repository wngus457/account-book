package com.juhyeon.calendar.feature.chart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.feature.chart.ChartContract
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.theme.Blue600
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray200
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray50
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius16
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius8
import com.juhyeon.calendar.shared.ui.system.theme.theme.Red500
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.departureNormal
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold
import com.juhyeon.calendar.shared.util.kotlin.extension.applyCommaFormat
import java.time.LocalDate

@Composable
internal fun ChartCard(
    isExpenditure: Boolean,
    isPieMode: Boolean,
    total: Long,
    windowStart: LocalDate,
    dailyAmounts: List<Long>,
    categoryAmounts: List<ChartContract.CategoryAmount>,
    onPieModeChange: (Boolean) -> Unit
) {
    val typeText = if (isExpenditure) "지출" else "수입"
    val accentColor = if (isExpenditure) Red500 else Blue600

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = Radius16)
            .border(width = 1.dp, color = Gray200, shape = Radius16)
            .background(White100, Radius16)
            .padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "이번 달 $typeText 합계",
                    style = MaterialTheme.typography.semiBold(12),
                    color = Gray400
                )
                Text(
                    text = "${total.applyCommaFormat()}원",
                    style = MaterialTheme.typography.departureNormal(26),
                    color = accentColor
                )
            }
            Row(
                modifier = Modifier
                    .clip(Radius10)
                    .background(Gray50)
                    .padding(3.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ModeButton(Icons.AutoMirrored.Filled.ShowChart, "선 그래프", !isPieMode) { onPieModeChange(false) }
                ModeButton(Icons.Filled.PieChart, "원형 그래프", isPieMode) { onPieModeChange(true) }
            }
        }

        if (isPieMode) {
            PieChart(
                isExpenditure = isExpenditure,
                categoryAmounts = categoryAmounts.filter { it.amount > 0 }
            )
        } else {
            LineChart(
                windowStart = windowStart,
                values = dailyAmounts,
                color = accentColor
            )
        }
    }
}

@Composable
private fun ModeButton(
    imageVector: ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val background = if (isSelected) Modifier.shadow(1.dp, Radius8).background(White100, Radius8) else Modifier

    Box(
        modifier = Modifier
            .size(30.dp)
            .then(background)
            .clip(Radius8)
            .clickableSingleIgnoreInteraction { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = if (isSelected) Gray800 else Gray400
        )
    }
}

@Composable
private fun LineChart(
    windowStart: LocalDate,
    values: List<Long>,
    color: Color
) {
    val textMeasurer = rememberTextMeasurer()
    val labelStyle = MaterialTheme.typography.normal(10).copy(color = Gray400)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        if (values.size < 2) return@Canvas

        val padH = 8.dp.toPx()
        val padTop = 12.dp.toPx()
        val padBottom = 22.dp.toPx()
        val plotW = size.width - padH * 2
        val plotH = size.height - padTop - padBottom
        val max = values.max().coerceAtLeast(1L).toFloat()

        val points = values.mapIndexed { i, v ->
            Offset(
                x = padH + i.toFloat() / (values.size - 1) * plotW,
                y = padTop + plotH - v / max * plotH
            )
        }
        val line = Path().apply {
            moveTo(points.first().x, points.first().y)
            // 제어점을 두 점의 x 중간에 각 점의 y 로 두면 곡선이 두 점 사이 y 범위를 벗어나지 않아
            // 0원 날도 바닥 아래로 꺼지지 않는다.
            points.zipWithNext { prev, next ->
                val midX = (prev.x + next.x) / 2
                cubicTo(midX, prev.y, midX, next.y, next.x, next.y)
            }
        }
        val area = Path().apply {
            addPath(line)
            lineTo(points.last().x, padTop + plotH)
            lineTo(points.first().x, padTop + plotH)
            close()
        }

        drawPath(
            path = area,
            brush = Brush.verticalGradient(
                colors = listOf(color.copy(alpha = 0.22f), color.copy(alpha = 0f)),
                startY = padTop,
                endY = padTop + plotH
            )
        )
        drawPath(
            path = line,
            color = color,
            style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // x 축 라벨은 처음 / 1/3 / 2/3 / 마지막 4곳만 찍는다.
        val last = values.size - 1
        listOf(0, (values.size * 0.33).toInt(), (values.size * 0.66).toInt(), last).distinct().forEach { i ->
            val layout = textMeasurer.measure("${windowStart.plusDays(i.toLong()).dayOfMonth}일", labelStyle)
            val x = (points[i].x - layout.size.width / 2f).coerceIn(0f, size.width - layout.size.width)
            drawText(layout, topLeft = Offset(x, size.height - layout.size.height))
        }
    }
}

@Composable
private fun PieChart(
    isExpenditure: Boolean,
    categoryAmounts: List<ChartContract.CategoryAmount>
) {
    val total = categoryAmounts.sumOf { it.amount }.coerceAtLeast(1L).toFloat()
    val top = categoryAmounts.maxByOrNull { it.amount }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val strokeWidth = 30.dp.toPx()
            val radius = 74.dp.toPx()
            val topLeft = Offset(center.x - radius, center.y - radius)
            val arcSize = Size(radius * 2, radius * 2)

            if (categoryAmounts.isEmpty()) {
                drawCircle(color = Gray50, radius = radius, style = Stroke(strokeWidth))
                return@Canvas
            }

            var startAngle = -90f
            categoryAmounts.forEach {
                val sweep = it.amount / total * 360f
                drawArc(
                    color = it.color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(strokeWidth)
                )
                startAngle += sweep
            }
        }

        if (top != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "최다 ${if (isExpenditure) "지출" else "수입"}",
                    style = MaterialTheme.typography.normal(11),
                    color = Gray400
                )
                Text(
                    text = top.category?.name ?: "미분류",
                    style = MaterialTheme.typography.semiBold(14),
                    color = Gray800
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChartCardLinePreview() {
    ChartCard(
        isExpenditure = true,
        isPieMode = false,
        total = 752000,
        windowStart = LocalDate.of(2026, 10, 1),
        dailyAmounts = List(30) { (10000 + (it * 7919 % 30000)).toLong() },
        categoryAmounts = emptyList(),
        onPieModeChange = { }
    )
}

@Preview
@Composable
private fun ChartCardPiePreview() {
    ChartCard(
        isExpenditure = true,
        isPieMode = true,
        total = 470000,
        windowStart = LocalDate.of(2026, 10, 1),
        dailyAmounts = emptyList(),
        categoryAmounts = listOf(
            ChartContract.CategoryAmount(Category("1", "식비", "meal"), 312000, Color(0xFFFF6B6B)),
            ChartContract.CategoryAmount(Category("2", "쇼핑", "cart"), 158000, Color(0xFFFFA94D))
        ),
        onPieModeChange = { }
    )
}