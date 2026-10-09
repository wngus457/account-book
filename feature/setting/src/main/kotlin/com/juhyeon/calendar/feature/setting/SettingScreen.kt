package com.juhyeon.calendar.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.juhyeon.calendar.feature.setting.component.BaseDayModal
import com.juhyeon.calendar.feature.setting.component.SettingItem
import com.juhyeon.calendar.shared.ui.common.extension.clickableSingle
import com.juhyeon.calendar.shared.ui.common.util.preview.VerticalPreview
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.Arrow
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.BasicTopNavigation
import com.juhyeon.calendar.shared.ui.system.theme.navigation.top.Title
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal

@Composable
fun SettingRoute(
    navController: NavHostController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    val postEvent = viewModel.eventHandler

    LaunchedEffect(true) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                else -> { }
            }
        }
    }
    var showBaseDayModal by remember { mutableStateOf(false) }

    SettingScreen(
        baseDay = viewModel.baseDay.intValue,
        onBaseDayClick = { showBaseDayModal = true }
    )

    BaseDayModal(
        show = showBaseDayModal,
        baseDay = viewModel.baseDay.intValue,
        onConfirm = {
            postEvent(SettingContract.Event.OnChangeBaseDay(it))
            showBaseDayModal = false
        },
        onDismiss = { showBaseDayModal = false }
    )
}

@Composable
private fun SettingScreen(
    baseDay: Int,
    onBaseDayClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = White100
    ) {
        Column(modifier = Modifier.padding(it)) {
            BasicTopNavigation(
                arrow = Arrow.Off,
                title = Title.On("설정")
            )

            SettingItem(
                title = "앱 설정",
                itemName = "달력 시작 일",
                item = {
                    Text(
                        modifier = Modifier.clickableSingle { onBaseDayClick() },
                        text = "${baseDay}일",
                        style = MaterialTheme.typography.normal(14)
                    )
                }
            )
        }
    }
}

@VerticalPreview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        baseDay = 10,
        onBaseDayClick = { }
    )
}