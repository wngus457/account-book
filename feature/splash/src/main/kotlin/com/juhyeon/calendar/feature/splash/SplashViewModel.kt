package com.juhyeon.calendar.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val reducer = MviReducer<SplashContract.Event, SplashContract.State, SplashContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    private fun initState() = SplashContract.State

    private fun handleEvent(event: SplashContract.Event) {

    }

    init {
        viewModelScope.launch {
            delay(3000L)
            reducer.setEffect(SplashContract.Effect.NavigateToHome)
        }
    }
}