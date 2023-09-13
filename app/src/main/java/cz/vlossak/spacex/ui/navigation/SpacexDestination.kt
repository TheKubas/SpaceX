package cz.vlossak.spacex.ui.navigation

import cz.vlossak.spacex.R

object SpacexDestination {
    const val SPACEX_HOME_SCREEN = "home_screen"
    const val LAUNCHES_SCREEN = "launches_screen"
    const val DETAIL_SCTEEN = "detail_screen"
}

sealed class NavigationDrawerDestinations(
    val route: String,
    val title: String,
    val selectedIcon: Int,
){
    object SpaceX : NavigationDrawerDestinations(
        route = SpacexDestination.SPACEX_HOME_SCREEN,
        title = "SpaceX",
        selectedIcon = R.drawable.ic_spacex
    )

    object Launches : NavigationDrawerDestinations(
        route = SpacexDestination.LAUNCHES_SCREEN,
        title = "Launches",
        selectedIcon = R.drawable.ic_space_ship

    )
}