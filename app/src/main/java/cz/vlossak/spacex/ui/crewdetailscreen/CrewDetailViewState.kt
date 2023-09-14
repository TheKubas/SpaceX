package cz.vlossak.spacex.ui.crewdetailscreen

import cz.vlossak.spacex.model.Crew
import cz.vlossak.spacex.model.LaunchDetail

data class CrewDetailViewState (
    val data: Crew = Crew(),
    val loading: Boolean = true,
    val error: String = ""
)