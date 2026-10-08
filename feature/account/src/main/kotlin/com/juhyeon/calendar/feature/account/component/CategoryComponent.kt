package com.juhyeon.calendar.feature.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import com.juhyeon.androidds.ui.icon.basic.BasicIcon
import com.juhyeon.androidds.ui.icon.basic.BasicIconSize
import com.juhyeon.androidds.ui.theme.Radius16
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingleIgnoreInteraction
import com.juhyeon.calendar.shared.ui.system.theme.icon.categoryIcon
import com.juhyeon.calendar.shared.ui.system.theme.theme.bold
import com.juhyeon.calendar.shared.ui.system.theme.theme.semiBold

private const val COLUMN_COUNT = 4

@Composable
internal fun CategoryComponent(
    categories: List<Category>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "카테고리",
            color = Color(0xFF7A7A7A),
            style = MaterialTheme.typography.bold(13)
        )

        // 바깥 verticalScroll 안에 있으므로 Lazy 대신 일반 Row로 4칸씩 배치
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            categories.chunked(COLUMN_COUNT).forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { category ->
                        CategoryItem(
                            modifier = Modifier.weight(1f),
                            category = category,
                            isSelected = category.categoryKey == selectedCategory,
                            onClick = { onCategorySelect(category.categoryKey) }
                        )
                    }
                    repeat(COLUMN_COUNT - row.size) { Spacer(Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (backgroundColor, contentColor) = if (isSelected) {
        Color(0xFFF4402C) to Color.White
    } else {
        Color(0xFFF6F6F6) to Color(0xFF7A7A7A)
    }
    Column(
        modifier = modifier.clickableSingleIgnoreInteraction { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(Radius16)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            BasicIcon(
                drawableRes = categoryIcon(category.icon),
                iconSize = BasicIconSize.Small,
                tint = contentColor
            )
        }

        Text(
            text = category.name,
            color = Color(0xFF7A7A7A),
            style = MaterialTheme.typography.semiBold(12)
        )
    }
}

@Preview
@Composable
private fun CategoryComponentPreview() {
    CategoryComponent(
        categories = listOf(Category(categoryKey = "1", name = "식비", icon = "meal")),
        selectedCategory = "1"
    )
}