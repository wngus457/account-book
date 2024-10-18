package com.juhyeon.calendar.navigation

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.juhyeon.calendar.feature.splash.SplashScreen
import com.juhyeon.calendar.network.NetworkContract
import com.juhyeon.calendar.network.NetworkViewModel
import com.juhyeon.calendar.shared.navigation.Splash
import com.juhyeon.calendar.shared.ui.common.extension.noAnimComposable
import com.juhyeon.calendar.shared.ui.system.theme.Normal14
import com.juhyeon.calendar.shared.ui.system.theme.modal.Modal
import com.juhyeon.calendar.shared.ui.system.theme.modal.ModalButtons
import com.juhyeon.calendar.shared.ui.system.theme.modal.ModalTitle

@Composable
fun RootNavGraph(
    navController: NavHostController,
    networkViewModel: NetworkViewModel = hiltViewModel()
) {
    val networkState = networkViewModel.stateFlow.collectAsState().value.networkState
    val activity = LocalContext.current as Activity

    Modal(
        show = networkState is NetworkContract.State.NetworkState.DisConnected,
        title = ModalTitle.On("알림"),
        content = @Composable {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "인터넷 연결이 원활하지 않습니다.\n네트워크 상태를 확인해주세요.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.Normal14
            )
        },
        buttons = ModalButtons.One("확인"),
        onLeftButtonClick = { activity.finishAffinity() }
    )

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        noAnimComposable<Splash> {
            SplashScreen(navController)
        }
    }
}