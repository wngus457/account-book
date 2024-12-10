package com.juhyeon.calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.juhyeon.calendar.feature.account.AddAccountScreen
import com.juhyeon.calendar.feature.home.HomeScreen
import com.juhyeon.calendar.shared.navigation.AddAccount
import com.juhyeon.calendar.shared.navigation.HomeNavGraph
import com.juhyeon.calendar.shared.navigation.NavigationRouteId
import com.juhyeon.calendar.shared.ui.common.extension.noAnimComposable
import com.juhyeon.calendar.shared.ui.common.extension.slideUpDownComposable

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation<HomeNavGraph>(
        startDestination = NavigationRouteId.Home
    ) {
        noAnimComposable<NavigationRouteId.Home> {
            HomeScreen(navController)
        }

        slideUpDownComposable<AddAccount> {
            AddAccountScreen(navController)
        }
    }
}