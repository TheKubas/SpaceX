package cz.vlossak.spacex.ui.launchdetailscreen

import cz.vlossak.spacex.model.LaunchDetail

data class LaunchDetailScreenViewState(
    val data: LaunchDetail = LaunchDetail(),
    val loading: Boolean = true,
    val error: String = ""
)