package cz.vlossak.spacex.model

import java.time.LocalDateTime

data class LaunchesDetail(
    val date_utc: LocalDateTime,
    val flight_number: Int,
    val id: String,
    val patchLarge: String,
    val patchSmall: String,
    val name: String,
)
data class LaunchesDetailDTO(
    val date_utc: String?,
    val flight_number: Int?,
    val id: String?,
    val links: LaunchLinksDTO,
    val name: String?,
)

data class LaunchLinksDTO(
    val patch: PatchDTO,
)

data class PatchDTO(
    val large: String?,
    val small: String?
)