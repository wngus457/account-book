package com.juhyeon.calendar.shared.ui.system.theme.navigation.top

sealed interface TopNavigationProperties

sealed interface Arrow : TopNavigationProperties {
    data object On : Arrow
    data object Off : Arrow
}

sealed interface Close : TopNavigationProperties {
    data object On : Close
    data object Off : Close
}

sealed interface Title : TopNavigationProperties {
    data class On(val title: String) : Title
    data object Off : Title
}