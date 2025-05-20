package com.juhyeon.calendar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.juhyeon.calendar.shared.ui.system.theme.theme.Black900
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink40
)

@Composable
fun CalendarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(
        key1 = systemUiController,
        key2 = darkTheme
    ) {
        systemUiController.setNavigationBarColor(
            color = White100,
            darkIcons = LightColorScheme.background.luminance() > 0.5,
            navigationBarContrastEnforced = false,
            transformColorForLightContent = { Black900.copy(alpha = 0.7f) }
        )
        systemUiController.setStatusBarColor(color = White100)

        onDispose { }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}