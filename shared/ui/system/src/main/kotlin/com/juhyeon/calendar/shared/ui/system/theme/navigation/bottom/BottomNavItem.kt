package com.juhyeon.calendar.shared.ui.system.theme.navigation.bottom

import androidx.compose.ui.graphics.vector.ImageVector
import com.juhyeon.calendar.shared.navigation.NavigationRouteId
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavSelectedHistory
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavSelectedHome
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavSelectedSetting
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavUnSelectedHistory
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavUnSelectedHome
import com.juhyeon.calendar.shared.ui.system.theme.icon.BottomNavUnSelectedSetting

sealed class BottomNavItem(
    val order: Int,
    val routeId: NavigationRouteId,
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Home : BottomNavItem(
        order = 1,
        routeId = NavigationRouteId.Home,
        name = "홈",
        selectedIcon = BottomNavSelectedHome,
        unselectedIcon = BottomNavUnSelectedHome
    )

    data object History : BottomNavItem(
        order = 2,
        routeId = NavigationRouteId.History,
        name = "내역",
        selectedIcon = BottomNavSelectedHistory,
        unselectedIcon = BottomNavUnSelectedHistory
    )

    data object Setting : BottomNavItem(
        order = 3,
        routeId = NavigationRouteId.Setting,
        name = "설정",
        selectedIcon = BottomNavSelectedSetting,
        unselectedIcon = BottomNavUnSelectedSetting
    )
}
