package cz.vlossak.spacex.ui.detailscreen

import cz.vlossak.spacex.model.LaunchDetail
import cz.vlossak.spacex.model.LaunchesDetail

data class DetailScreenViewState(
    val data: LaunchDetail = LaunchDetail(),
    val loading: Boolean = true,
    val error: String = ""
)