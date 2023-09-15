package cz.vlossak.spacex.mapper

import cz.vlossak.spacex.model.Crew
import cz.vlossak.spacex.model.CrewDTO
import javax.inject.Inject

class CrewMapper @Inject constructor() : Mapper<CrewDTO, Crew> {
    override fun map(from: CrewDTO) =
        Crew(
            agency = from.agency ?: "",
    id =  from.id ?: "",
    image =  from.image ?: "",
    numberOfLaunches =  from.launches?.size ?: 0,
    name =  from.name ?: "",
    status =  from.status ?: "",
    wikipedia =  from.wikipedia ?: ""
        )

    fun mapList(from: List<CrewDTO>): List<Crew> {
        return from.map { map(it) }
    }
}