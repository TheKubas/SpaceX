package cz.vlossak.spacex.ui.launchesscreen

import cz.vlossak.spacex.model.LaunchDetail

data class LaunchesScreenViewState (
    val data: List<LaunchDetail> = emptyList(),
    val error: String = "",
    val loading: Boolean = true
)