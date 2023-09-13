package cz.vlossak.spacex.model

data class CompanyDetails(
    val ceo: String = "",
    val coo: String = "",
    val cto: String = "",
    val cto_propulsion: String = "",
    val name: String = "",
    val summary: String = "",
    val website: String = ""
)

data class CompanyDetailsDTO(
    val ceo: String?,
    val coo: String?,
    val cto: String?,
    val cto_propulsion: String?,
    val employees: Int?,
    val founder: String?,
    val links: LinksDTO,
    val name: String?,
    val summary: String?,
)

data class LinksDTO(
    val elon_twitter: String?,
    val flickr: String?,
    val twitter: String?,
    val website: String?
)