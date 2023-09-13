package cz.vlossak.spacex.ui.navigation

import cz.vlossak.spacex.R

object SpacexDestination {
    const val SPACEX_HOME_SCREEN = "home_screen"
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
}