package com.juhyeon.calendar.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val reducer = MviReducer<SplashContract.Event, SplashContract.State, SplashContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val state = reducer.stateFlow
    val effect = reducer.effectFlow
    val eventHandler = ::handleEvent

    private fun initState() = SplashContract.State

    private fun handleEvent(event: SplashContract.Event) {

    }

    init {

    }
}