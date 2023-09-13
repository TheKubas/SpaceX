package cz.vlossak.spacex.ui.launchesscreen

import cz.vlossak.spacex.model.LaunchesDetail

data class LaunchesScreenViewState (
    val data: List<LaunchesDetail> = emptyList(),
    val error: String = "",
    val loading: Boolean = true
)