package com.juhyeon.calendar.shared.ui.system.theme.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.juhyeon.calendar.shared.ui.common.extension.multipleEventsCutter
import com.juhyeon.calendar.shared.ui.system.theme.theme.Radius8
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal

@Composable
fun ButtonBasic(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    onClick: () -> Unit,
    flexible: ButtonFlexible = ButtonFlexible.True,
    form: ButtonForm = ButtonForm.Filled,
    state: ButtonState = ButtonState.Enabled,
    contentPadding: Dp = 0.dp
) {
    val colorSet = when (state) {
        ButtonState.Enabled -> {
            when (form) {
                ButtonForm.Filled -> ButtonColor.PrimaryFilled()
                ButtonForm.Line -> ButtonColor.PrimaryLine()
            }
        }
        ButtonState.Disabled -> {
            when (form) {
                ButtonForm.Filled -> ButtonColor.PrimaryFilled()
                ButtonForm.Line -> ButtonColor.PrimaryLine()
            }
        }
        else -> { ButtonColor.PrimaryFilled() }
    }

    multipleEventsCutter { cutter ->
        Button(
            modifier = modifier.let {
                when (flexible) {
                    ButtonFlexible.True -> it.wrapContentWidth()
                    ButtonFlexible.False -> it.fillMaxWidth()
                }
            },
            contentPadding = PaddingValues(contentPadding),
            enabled = state != ButtonState.Disabled,
            colors = colorSet.buttonColors(),
            shape = Radius8,
            border = BorderStroke(1.dp, colorSet.borderColor),
            onClick = { cutter.processEvent(onClick) }
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = text,
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonBasicPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        ButtonBasic(
            text = "테스트",
            textStyle = MaterialTheme.typography.normal(14),
            onClick = { }
        )
    }
}