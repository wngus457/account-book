package com.juhyeon.calendar.feature.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import com.juhyeon.calendar.shared.navigation.AddAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    private val reducer = MviReducer<AddAccountContract.Event, AddAccountContract.State, AddAccountContract.Effect>(
        viewModelScope = viewModelScope,
        initialState = initState(),
        handleEvent = ::handleEvent
    )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow =  reducer.effectFlow

    private val year = state.toRoute<AddAccount>().year
    private val month = state.toRoute<AddAccount>().month
    private val date = state.toRoute<AddAccount>().date

    private fun initState() = AddAccountContract.State(
        expense = null
    )

    private fun handleEvent(event: AddAccountContract.Event) {
        when (event) {
            is AddAccountContract.Event.OnBackClick -> reducer.setEffect(AddAccountContract.Effect.NavigateToBack)
        }
    }

    init {

    }

}