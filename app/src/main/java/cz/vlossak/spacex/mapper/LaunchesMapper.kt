package cz.vlossak.spacex.mapper

import cz.vlossak.spacex.extension.convertDate
import cz.vlossak.spacex.model.LaunchesDetail
import cz.vlossak.spacex.model.LaunchesDetailDTO
import javax.inject.Inject

class LaunchesMapper @Inject constructor() : Mapper<LaunchesDetailDTO, LaunchesDetail> {
    override fun map(from: LaunchesDetailDTO) =
        LaunchesDetail(
            date_utc = convertDate(from.date_utc ?: ""),
            flight_number = from.flight_number ?: 0,
            id = from.id ?: "",
            patchSmall = from.links.patch.small ?: "",
            name = from.name ?: ""

        )

    fun mapList(from: List<LaunchesDetailDTO>): List<LaunchesDetail> {
        return from.map { map(it) }
    }
}