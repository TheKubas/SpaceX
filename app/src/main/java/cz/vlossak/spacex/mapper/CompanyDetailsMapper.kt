package cz.vlossak.spacex.mapper

import cz.vlossak.spacex.model.CompanyDetails
import cz.vlossak.spacex.model.CompanyDetailsDTO
import javax.inject.Inject

class CompanyDetailsMapper @Inject constructor() : Mapper<CompanyDetailsDTO, CompanyDetails> {
    override fun map(from: CompanyDetailsDTO) =
        CompanyDetails(
            ceo = from.ceo ?: "",
            coo = from.coo ?: "",
            cto = from.cto ?: "",
            cto_propulsion = from.cto_propulsion ?: "",
            name = from.name ?: "",
            summary = from.summary ?: "",
            website = from.links.website ?: ""
        )
}