package cz.vlossak.spacex.model

data class Crew(
    val agency: String = "",
    val id: String = "",
    val image: String = "",
    val numberOfLaunches: Int = 0,
    val name: String = "",
    val status: String = "",
    val wikipedia: String = ""
)
data class CrewDTO(
    val agency: String?,
    val id: String?,
    val image: String?,
    val launches: List<String>?,
    val name: String?,
    val status: String?,
    val wikipedia: String?
)