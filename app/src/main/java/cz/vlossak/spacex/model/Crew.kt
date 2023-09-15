package cz.vlossak.spacex.model

import com.squareup.moshi.Json

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
    @Json(name = "agency")
    val agency: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "launches")
    val launches: List<String>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?
)