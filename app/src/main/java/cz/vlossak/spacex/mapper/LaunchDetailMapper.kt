package cz.vlossak.spacex.mapper

import cz.vlossak.spacex.extension.convertDate
import cz.vlossak.spacex.model.LaunchDetail
import cz.vlossak.spacex.model.LaunchDetailDTO
import javax.inject.Inject

class LaunchDetailMapper @Inject constructor() : Mapper<LaunchDetailDTO, LaunchDetail> {
    override fun map(from: LaunchDetailDTO) =
        LaunchDetail(
            date_utc = convertDate(from.date_utc ?: ""),
            details = from.details ?: "",
            flight_number = from.flight_number ?: 0,
            id = from.id ?: "",
            launchpad = from.launchpad ?: "",
            name = from.name ?: "",
            rocket = from.rocket ?: "",
            success = from.success,
            large = from.links.patch.large ?: "",
            small = from.links.patch.small ?: ""
        )
}