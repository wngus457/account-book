package com.juhyeon.calendar.shared.ui.system.theme.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.system.theme.Black900
import com.juhyeon.calendar.shared.ui.system.theme.Gray200
import com.juhyeon.calendar.shared.ui.system.theme.Medium16
import com.juhyeon.calendar.shared.ui.system.theme.SemiBold18
import com.juhyeon.calendar.shared.ui.system.theme.White100

@Composable
fun Modal(
    show: Boolean,
    title: ModalTitle = ModalTitle.Off,
    content: @Composable () -> Unit,
    buttons: ModalButtons = ModalButtons.One(text = "확인"),
    onLeftButtonClick: () -> Unit = { },
    onRightButtonClick: () -> Unit = { },
    onDismiss: () -> Unit = { }
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            AlertDialogContent(
                title = title,
                content = content,
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        when (buttons) {
                            is ModalButtons.One -> BasicModalButton(text = buttons.text, onClick = onLeftButtonClick)
                            is ModalButtons.Two -> {
                                BasicModalButton(text = buttons.leftText, onClick = onLeftButtonClick)
                                BasicModalButton(text = buttons.rightText, onClick = onRightButtonClick)
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}

@Composable
private fun AlertDialogContent(
    title: ModalTitle,
    content: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = White100
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = shape,
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 40.dp, end = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (title is ModalTitle.On) {
                    BasicModalTitle(title.text)
                }
                content()
            }
            HorizontalDivider(color = Gray200)
            buttons()
        }
    }
}

@Composable
private fun BasicModalTitle(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.SemiBold18,
        color = Black900
    )
}

@Composable
private fun RowScope.BasicModalButton(
    text: String,
    textColor: Color = Black900,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(52.dp)
            .clickableSingle { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.Medium16
        )
    }
}