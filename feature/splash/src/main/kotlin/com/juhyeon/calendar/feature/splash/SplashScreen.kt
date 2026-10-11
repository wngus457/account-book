package com.juhyeon.calendar.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.shared.navigation.NavigationRouteId
import com.juhyeon.calendar.shared.ui.common.util.preview.FoldPreview
import com.juhyeon.calendar.shared.ui.common.util.preview.VerticalPreview
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val state = splashViewModel.stateFlow.collectAsState().value
    val postEvent = splashViewModel.eventHandler

    LaunchedEffect(true) {
        splashViewModel.effectFlow.collect { effect ->
            when (effect) {
                SplashContract.Effect.NavigateToBack -> navController.popBackStack()
                SplashContract.Effect.NavigateToHome -> navController.navigate(NavigationRouteId.Home) {
                    popUpTo<NavigationRouteId.Splash> { inclusive = true }
                }
            }
        }
    }

    SplashContents(
        onClick = { postEvent(SplashContract.Event.OnBackClick) }
    )
}

@Composable
private fun SplashContents(
    onClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF22A98F))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(com.juhyeon.calendar.shared.ui.system.R.drawable.splash),
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
    }
}


@FoldPreview
@VerticalPreview
@Composable
private fun SplashContentsPreview() {
    SplashContents()
}