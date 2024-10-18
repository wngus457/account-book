package com.juhyeon.calendar.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val networkChecker: NetworkChecker
) : ViewModel() {

    private val reducer = MviReducer<NetworkContract.Event, NetworkContract.State, NetworkContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    private fun initState() = NetworkContract.State(
        networkState = networkChecker.networkStateFlow.value
    )

    private fun handleEvent(event: NetworkContract.Event) { }

    init {
        networkChecker.networkStateFlow
            .onEach {
                reducer.setState { copy(networkState = it) }
            }
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }
}