package cz.vlossak.spacex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.vlossak.spacex.ui.companyscreen.CompanyDetailsScreen
import cz.vlossak.spacex.ui.launchesscreen.LaunchesScreen

@Composable
fun SpacexNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = SpacexDestination.SPACEX_HOME_SCREEN
    ) {
        composable(SpacexDestination.SPACEX_HOME_SCREEN) {
            CompanyDetailsScreen()
        }
        composable(SpacexDestination.LAUNCHES_SCREEN) {
            LaunchesScreen()
        }
    }

}