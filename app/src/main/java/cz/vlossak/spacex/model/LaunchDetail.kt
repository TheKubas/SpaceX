package cz.vlossak.spacex.model

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class LaunchDetail(
    val dateUtc: LocalDateTime = LocalDateTime.now(),
    val details: String = "",
    val flightNumber: Int = 0,
    val id: String = "",
    val launchpad: String = "",
    val name: String = "",
    val rocket: String = "",
    val success: Boolean? = null,
    val large: String = "",
    val small: String = ""
)

data class LaunchDetailDTO(
    @Json(name = "date_utc")
    val dateUtc: String?,
    @Json(name = "details")
    val details: String?,
    @Json(name = "flight_number")
    val flightNumber: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "launchpad")
    val launchpad: String?,
    @Json(name = "links")
    val links: LaunchLinksDTO,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rocket")
    val rocket: String?,
    @Json(name = "success")
    val success: Boolean?,
)

data class LaunchLinksDTO(
    val patch: LaunchPatchDTO,
)

data class LaunchPatchDTO(
    val large: String?,
    val small: String?
)