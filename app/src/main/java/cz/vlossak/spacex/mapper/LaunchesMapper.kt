package cz.vlossak.spacex.mapper

import cz.vlossak.spacex.extension.convertDate
import cz.vlossak.spacex.model.LaunchDetail
import cz.vlossak.spacex.model.LaunchDetailDTO
import javax.inject.Inject

class LaunchesMapper @Inject constructor() : Mapper<LaunchDetailDTO, LaunchDetail> {
    override fun map(from: LaunchDetailDTO) =
        LaunchDetail(
            dateUtc = convertDate(from.dateUtc ?: ""),
            details = from.details ?: "",
            flightNumber = from.flightNumber ?: 0,
            id = from.id ?: "",
            launchpad = from.launchpad ?: "",
            name = from.name ?: "",
            rocket = from.rocket ?: "",
            success = from.success,
            large = from.links.patch.large ?: "",
            small = from.links.patch.small ?: ""

        )

    fun mapList(from: List<LaunchDetailDTO>): List<LaunchDetail> {
        return from.map { map(it) }
    }
}