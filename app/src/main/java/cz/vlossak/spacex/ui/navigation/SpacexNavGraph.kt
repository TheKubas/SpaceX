package cz.vlossak.spacex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.vlossak.spacex.ui.companyscreen.CompanyDetailsScreen
import cz.vlossak.spacex.ui.crewdetailscreen.CrewDetailScreen
import cz.vlossak.spacex.ui.crewsrceen.CrewScreen
import cz.vlossak.spacex.ui.launchdetailscreen.LaunchDetailScreen
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
            LaunchesScreen(navigateToDetail = { launchId ->
                navController.navigate("${SpacexDestination.LAUNCH_DETAIL_SCREEN}/${launchId}")
            })
        }
        composable(
            route = "${SpacexDestination.LAUNCH_DETAIL_SCREEN}/{${SpacexNavArguments.LAUNCH_ID}}",
            arguments = listOf(
                navArgument(SpacexNavArguments.LAUNCH_ID) {
                    type = NavType.StringType
                }
            )) {
            LaunchDetailScreen()
        }
        composable(SpacexDestination.CREW_SCREEN) {
            CrewScreen(navigateToDetail = { personId ->
                navController.navigate("${SpacexDestination.CREW_DETAIL_SCREEN}/${personId}")
            })
        }
        composable(
            route = "${SpacexDestination.CREW_DETAIL_SCREEN}/{${SpacexNavArguments.PERSON_ID}}",
            arguments = listOf(
                navArgument(SpacexNavArguments.PERSON_ID) {
                    type = NavType.StringType
                }
            )) {
            CrewDetailScreen()
        }
    }

}