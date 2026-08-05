package com.juhyeon.calendar.feature.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.InsertCategoryUseCase
import com.juhyeon.calendar.domain.onSuccess
import com.juhyeon.calendar.domain.setting.app.GetFirstAppStartUseCase
import com.juhyeon.calendar.domain.setting.app.SetFirstAppStartUseCase
import com.juhyeon.calendar.shared.core.mvi.MviReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getFirstAppStartUseCase: GetFirstAppStartUseCase,
    private val setFirstAppStartUseCase: SetFirstAppStartUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase
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
        getFirstAppStartUseCase()
            .onSuccess {
                if(it) {
                    setFirstAppStart()
                    setCategory()
                }
                delay(3000L.milliseconds)
                reducer.setEffect(SplashContract.Effect.NavigateToHome)
            }
            .launchIn(viewModelScope)
    }

    private fun setFirstAppStart() {
        setFirstAppStartUseCase(false)
            .launchIn(viewModelScope)
    }

    private fun setCategory() {
        val category = Category(
            categoryKey = "0",
            name = "식비"
        )
        insertCategoryUseCase(category)
            .onSuccess { Log.e("테스트", category.toString()) }
            .launchIn(viewModelScope)
    }
}