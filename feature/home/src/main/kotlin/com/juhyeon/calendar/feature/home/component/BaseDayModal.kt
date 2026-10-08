package com.juhyeon.calendar.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.domain.setting.baseday.MAX_BASE_DAY
import com.juhyeon.calendar.domain.setting.baseday.MIN_BASE_DAY
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.modal.Modal
import com.juhyeon.calendar.shared.ui.system.theme.modal.ModalButtons
import com.juhyeon.calendar.shared.ui.system.theme.modal.ModalTitle
import com.juhyeon.calendar.shared.ui.system.theme.theme.Black900
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius10
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal

@Composable
internal fun BaseDayModal(
    show: Boolean,
    baseDay: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var selected by remember { mutableIntStateOf(baseDay) }

    LaunchedEffect(show, baseDay) {
        if (show) selected = baseDay
    }

    Modal(
        show = show,
        title = ModalTitle.On("기준일 선택"),
        content = {
            LazyVerticalGrid(
                modifier = Modifier.height(220.dp),
                columns = GridCells.Fixed(7)
            ) {
                items((MIN_BASE_DAY..MAX_BASE_DAY).toList()) { day ->
                    BaseDayCell(
                        day = day,
                        isSelected = day == selected,
                        onClick = { selected = day }
                    )
                }
            }
        },
        buttons = ModalButtons.Two(leftText = "취소", rightText = "확인"),
        onLeftButtonClick = onDismiss,
        onRightButtonClick = { onConfirm(selected) },
        onDismiss = onDismiss
    )
}

@Composable
private fun BaseDayCell(
    day: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val background = if (isSelected) Modifier.background(Gray400) else Modifier

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = Radius10)
            .then(background)
            .clickableSingle { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            style = MaterialTheme.typography.normal(16),
            color = if (isSelected) White100 else Black900
        )
    }
}

@Preview
@Composable
private fun BaseDayModalPreview() {
    BaseDayModal(
        show = true,
        baseDay = 10,
        onConfirm = { },
        onDismiss = { }
    )
}