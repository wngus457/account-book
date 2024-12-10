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
    val date: String
)