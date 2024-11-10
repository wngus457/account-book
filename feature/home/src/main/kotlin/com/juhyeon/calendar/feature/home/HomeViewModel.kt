package com.juhyeon.calendar.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val reducer = MviReducer<HomeContract.Event, HomeContract.State, HomeContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    private fun initState() = HomeContract.State

    private fun handleEvent(event: HomeContract.Event) {

    }
}