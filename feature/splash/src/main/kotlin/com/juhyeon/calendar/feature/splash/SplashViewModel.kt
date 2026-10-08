package com.juhyeon.calendar.feature.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhyeon.calendar.domain.category.Category
import com.juhyeon.calendar.domain.category.insert.InsertCategoriesUseCase
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
    private val insertCategoriesUseCase: InsertCategoriesUseCase
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
        val categories = listOf(
            Category(name = "식비", icon = "meal"),
            Category(name = "교통비", icon = "car"),
            Category(name = "쇼핑", icon = "cart"),
            Category(name = "외식", icon = "pizza"),
            Category(name = "주거", icon = "apartment"),
            Category(name = "의료", icon = "medical"),
            Category(name = "교육", icon = "book"),
            Category(name = "육아", icon = "baby"),
            Category(name = "운동", icon = "exercise"),
            Category(name = "취미", icon = "game"),
            Category(name = "여행", icon = "airplane"),
            Category(name = "저축", icon = "savings"),
            Category(name = "투자", icon = "stock"),
            Category(name = "상금", icon = "trophy"),
            Category(name = "기타", icon = "money")
        )
        insertCategoriesUseCase(categories)
            .onSuccess { Log.e("테스트", categories.toString()) }
            .launchIn(viewModelScope)
    }
}