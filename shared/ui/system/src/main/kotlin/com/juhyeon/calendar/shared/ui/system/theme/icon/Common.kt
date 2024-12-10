package com.juhyeon.calendar.shared.ui.system.theme.icon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

val CommonArrowBack = Icons.AutoMirrored.Filled.KeyboardArrowLeft
val CommonArrowForward = Icons.AutoMirrored.Filled.KeyboardArrowRight
val CommonClose = Icons.Filled.Close
val CommonBack = Icons.Filled.ArrowBackIosNew

val BottomNavSelectedHome = Icons.Filled.Home
val BottomNavUnSelectedHome = Icons.Outlined.Home
val BottomNavSelectedSetting = Icons.Filled.Settings
val BottomNavUnSelectedSetting = Icons.Outlined.Settings
val BottomNavSelectedHistory = Icons.Filled.Timer
val BottomNavUnSelectedHistory = Icons.Outlined.Timer


@Preview(showBackground = true)
@Composable
private fun CommonIconsPreview() {
    Column {
        Icon(imageVector = CommonArrowBack, contentDescription = "CommonArrowBack")
        Icon(imageVector = CommonArrowForward, contentDescription = "CommonArrowForward")
    }
}