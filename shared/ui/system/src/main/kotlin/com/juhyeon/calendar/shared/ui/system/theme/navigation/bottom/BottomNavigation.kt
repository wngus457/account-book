package com.juhyeon.calendar.shared.ui.system.theme.navigation.bottom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import com.juhyeon.calendar.shared.ui.system.theme.theme.Black900
import com.juhyeon.calendar.shared.ui.system.theme.theme.Gray300
import com.juhyeon.calendar.shared.ui.system.theme.theme.White100
import com.juhyeon.calendar.shared.ui.system.theme.theme.normal

@Composable
fun <T : BottomNavItem> BottomNavigation(
    navList: List<T>,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navOptionsBuilder = NavOptions
        .Builder()
        .setLaunchSingleTop(true)
        .setRestoreState(true)

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(elevation = 8.dp),
        containerColor = White100,
        contentColor = White100
    ) {
        navList.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.route == item.routeId::class.qualifiedName) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        color = if (currentDestination?.route == item.routeId::class.qualifiedName) Black900 else Gray300,
                        style = MaterialTheme.typography.normal(14)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = White100,
                    selectedIconColor = Black900,
                    selectedTextColor = Black900,
                    unselectedIconColor = Gray300,
                    unselectedTextColor = Gray300
                ),
                selected = currentDestination?.route == item.routeId::class.qualifiedName,
                alwaysShowLabel = true,
                onClick = {

                }
            )
        }
    }
}