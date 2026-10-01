package com.juhyeon.calendar.shared.ui.common.util.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
    widthDp = 360,
    heightDp = 780,
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark theme",
    widthDp = 360,
    heightDp = 780,
    showBackground = true
)
annotation class VerticalPreviews

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
    widthDp = 780,
    heightDp = 360,
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark theme",
    widthDp = 780,
    heightDp = 360,
    showBackground = true
)
annotation class HorizontalPreviews

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
    widthDp = 360,
    heightDp = 780,
    showBackground = true
)
annotation class VerticalPreview

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
    widthDp = 780,
    heightDp = 360,
    showBackground = true
)
annotation class HorizontalPreview