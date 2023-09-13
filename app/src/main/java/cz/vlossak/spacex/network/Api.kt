package cz.vlossak.spacex.network

import cz.vlossak.spacex.model.CompanyDetailsDTO
import retrofit2.http.GET

interface Api {
    @GET("/v4/company")
    suspend fun getCompanyDetails(): CompanyDetailsDTO
}