package com.juhyeon.calendar.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juhyeon.calendar.shared.ui.system.theme.White100

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val state = splashViewModel.state.collectAsState().value
    val postEvent = splashViewModel.eventHandler

    LaunchedEffect(true) {
        splashViewModel.effect.collect { effect ->
            when (effect) {
                SplashContract.Effect.NavigateToBack -> navController.popBackStack()
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
            .background(White100)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun SplashContentsPreview() {
    SplashContents()
}