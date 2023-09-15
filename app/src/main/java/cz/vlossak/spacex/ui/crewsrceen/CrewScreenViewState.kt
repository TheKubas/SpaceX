package cz.vlossak.spacex.ui.crewsrceen

import cz.vlossak.spacex.model.Crew

data class CrewScreenViewState (
    val data: List<Crew> = emptyList(),
    val loading: Boolean = true,
    val error: String = ""
)