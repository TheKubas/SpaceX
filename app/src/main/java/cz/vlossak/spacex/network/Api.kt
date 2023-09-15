package cz.vlossak.spacex.network

import cz.vlossak.spacex.model.CompanyDetailsDTO
import cz.vlossak.spacex.model.CrewDTO
import cz.vlossak.spacex.model.LaunchDetailDTO
import cz.vlossak.spacex.model.LaunchesDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/v4/company")
    suspend fun getCompanyDetails(): CompanyDetailsDTO

    @GET("v5/launches/past")
    suspend fun getLaunches(): List<LaunchesDetailDTO>

    @GET("v5/launches/{launchId}")
    suspend fun getLaunchDetail(@Path("launchId") capsuleId: String): LaunchDetailDTO

    @GET("v4/crew")
    suspend fun getCrew(): List<CrewDTO>

}