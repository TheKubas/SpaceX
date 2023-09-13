package cz.vlossak.spacex.model

import java.time.LocalDateTime

data class LaunchesDetail(
    val date_utc: LocalDateTime = LocalDateTime.now(),
    val flight_number: Int = 0,
    val id: String = "",
    val patchSmall: String = "",
    val name: String = "",
)
data class LaunchesDetailDTO(
    val date_utc: String?,
    val flight_number: Int?,
    val id: String?,
    val links: LaunchesLinksDTO,
    val name: String?,
)

data class LaunchesLinksDTO(
    val patch: PatchDTO,
)