package cz.vlossak.spacex.model

import java.time.LocalDateTime

data class LaunchDetail (
    val date_utc: LocalDateTime = LocalDateTime.now(),
    val details: String = "",
    val flight_number: Int = 0,
    val id: String = "",
    val launchpad: String = "",
    val name: String = "",
    val rocket: String = "",
    val success: Boolean? = null,
    val large: String = "",
    val small: String = ""
)

data class LaunchDetailDTO(
    val date_utc: String?,
    val details: String?,
    val flight_number: Int?,
    val id: String?,
    val launchpad: String?,
    val links: LaunchLinksDTO,
    val name: String?,
    val rocket: String?,
    val success: Boolean?,
)
data class LaunchLinksDTO(
    val patch: LaunchPatchDTO,
)

data class LaunchPatchDTO(
    val large: String?,
    val small: String?
)
