package com.juhyeon.calendar.shared.navigation

import kotlinx.serialization.Serializable

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