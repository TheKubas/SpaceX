package cz.vlossak.spacex.network

import cz.vlossak.spacex.model.CompanyDetailsDTO
import cz.vlossak.spacex.model.LaunchesDetailDTO
import retrofit2.http.GET

interface Api {
    @GET("/v4/company")
    suspend fun getCompanyDetails(): CompanyDetailsDTO

    @GET("v5/launches/past")
    suspend fun getLaunches(): List<LaunchesDetailDTO>
}