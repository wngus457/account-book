package com.juhyeon.calendar.shared.ui.system.theme.navigation.bottom

import com.juhyeon.calendar.shared.navigation.NavigationRouteId
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavChart
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavSelectedHome
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavSelectedSetting
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavUnSelectedHome
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavUnSelectedSetting

sealed class BottomNavItem(
    val order: Int,
    val routeId: NavigationRouteId,
    val name: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    data object Home : BottomNavItem(
        order = 1,
        routeId = NavigationRouteId.Home,
        name = "홈",
        selectedIcon = BottomNavSelectedHome,
        unselectedIcon = BottomNavUnSelectedHome
    )

    data object Chart : BottomNavItem(
        order = 2,
        routeId = NavigationRouteId.Chart,
        name = "분석",
        selectedIcon = BottomNavChart,
        unselectedIcon = BottomNavChart
    )

    data object Setting : BottomNavItem(
        order = 3,
        routeId = NavigationRouteId.Setting,
        name = "설정",
        selectedIcon = BottomNavSelectedSetting,
        unselectedIcon = BottomNavUnSelectedSetting
    )
}
