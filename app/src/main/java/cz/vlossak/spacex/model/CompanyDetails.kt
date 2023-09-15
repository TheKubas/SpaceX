package cz.vlossak.spacex.model

import com.squareup.moshi.Json

data class CompanyDetails(
    val ceo: String = "",
    val coo: String = "",
    val cto: String = "",
    val ctoPropulsion: String = "",
    val name: String = "",
    val summary: String = "",
    val website: String = ""
)

data class CompanyDetailsDTO(
    @Json(name = "ceo")
    val ceo: String?,
    @Json(name = "coo")
    val coo: String?,
    @Json(name = "cto")
    val cto: String?,
    @Json(name = "cto_propulsion")
    val ctoPropulsion: String?,
    @Json(name = "employees")
    val employees: Int?,
    @Json(name = "founder")
    val founder: String?,
    @Json(name = "links")
    val links: LinksDTO,
    @Json(name = "name")
    val name: String?,
    @Json(name = "summary")
    val summary: String?,
)

data class LinksDTO(
    @Json(name = "elon_twitter")
    val elonTwitter: String?,
    @Json(name = "flickr")
    val flickr: String?,
    @Json(name = "twitter")
    val twitter: String?,
    @Json(name = "website")
    val website: String?
)