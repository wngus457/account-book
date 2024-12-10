package com.juhyeon.calendar.shared.ui.system.theme.button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.juhyeon.calendar.shared.ui.system.theme.Gray300
import com.juhyeon.calendar.shared.ui.system.theme.Gray400
import com.juhyeon.calendar.shared.ui.system.theme.Gray800
import com.juhyeon.calendar.shared.ui.system.theme.Red300
import com.juhyeon.calendar.shared.ui.system.theme.White100

sealed interface ButtonColor {
    val backgroundColor: Color
    val borderColor: Color
    val textColor: Color
    val backgroundDisabledColor: Color
    val borderDisabledColor: Color
    val textDisabledColor: Color

    data class PrimaryFilled(
        override val backgroundColor: Color = Red300,
        override val borderColor: Color = Gray800,
        override val textColor: Color = Gray800,
        override val backgroundDisabledColor: Color = Gray300,
        override val borderDisabledColor: Color = Gray400,
        override val textDisabledColor: Color = Gray400
    ) : ButtonColor

    data class PrimaryLine(
        override val backgroundColor: Color = White100,
        override val borderColor: Color = Gray800,
        override val textColor: Color = Gray800,
        override val backgroundDisabledColor: Color = Gray300,
        override val borderDisabledColor: Color = Gray400,
        override val textDisabledColor: Color = Gray400
    ) : ButtonColor
}

@Composable
fun ButtonColor.buttonColors() = ButtonDefaults.buttonColors(
    containerColor = backgroundColor,
    contentColor = textColor,
    disabledContentColor = textDisabledColor,
    disabledContainerColor = backgroundDisabledColor
)