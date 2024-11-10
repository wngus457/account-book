package com.juhyeon.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.juhyeon.calendar.navigation.RootNavGraph
import com.juhyeon.calendar.shared.ui.system.theme.navigation.bottom.BottomNavItem
import com.juhyeon.calendar.shared.ui.system.theme.navigation.bottom.BottomNavigation

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val list = BottomNavItem::class.sealedSubclasses.map { it.objectInstance as BottomNavItem }.sortedBy { it.order }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navList = list,
                navController = navController
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            RootNavGraph(
                navController = navController
            )
        }
    }
}