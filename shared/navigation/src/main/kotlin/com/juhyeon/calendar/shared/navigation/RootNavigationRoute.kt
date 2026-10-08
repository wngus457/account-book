package com.juhyeon.calendar.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeNavGraph

sealed class NavigationRouteId {
    @Serializable
    data object Splash : NavigationRouteId()

    @Serializable
    data object Home : NavigationRouteId()

    @Serializable
    data object Setting : NavigationRouteId()

    @Serializable
    data object History : NavigationRouteId()
}

@Serializable
data class AddAccount(
    val year: String,
    val month: String,
    val date: String,
    // 아래는 수정 모드일 때만 채운다. expenseKey 가 비어 있으면 새 내역 추가.
    val expenseKey: String = "",
    val price: Long = 0L,
    val time: String = "",
    val memo: String = "",
    val categoryKey: String = "0",
    val isExpenditure: Boolean = true
)