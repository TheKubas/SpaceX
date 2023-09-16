package cz.vlossak.spacex.network

import cz.vlossak.spacex.extension.Either
import cz.vlossak.spacex.mapper.CompanyDetailsMapper
import cz.vlossak.spacex.mapper.CrewMapper
import cz.vlossak.spacex.mapper.LaunchesMapper
import cz.vlossak.spacex.model.CompanyDetails
import cz.vlossak.spacex.model.Crew
import cz.vlossak.spacex.model.LaunchDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
    private val companyDetailsMapper: CompanyDetailsMapper,
    private val launchesMapper: LaunchesMapper,
    private val crewMapper: CrewMapper
) {

    var crew : List<Crew> = emptyList()
    private var launches : List<LaunchDetail> = emptyList()


    suspend fun getCompanyDetails(): Either<String, CompanyDetails> {
        return try {
            val companyDetails = api.getCompanyDetails()
            Either.Value(companyDetailsMapper.map(companyDetails))
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

    suspend fun getLaunches(): Either<String, List<LaunchDetail>> {
        return try {
            val launchesFromApi = api.getLaunches()
            launches = launchesMapper.mapList(launchesFromApi)
            Either.Value(launchesMapper.mapList(launchesFromApi))
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

    fun getLaunchDetail(launchId: String): Either<String, LaunchDetail> {
        return try {
            val launch = launches.find { it.id == launchId }
            if (launch != null) {
                Either.Value(launch)
            } else {
                Either.Error("Launch not found")
            }
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

    suspend fun getCrew() : Either<String, List<Crew>>{
        return try {
            val crewFromApi = api.getCrew()
            crew = crewMapper.mapList(crewFromApi)
            Either.Value(crewMapper.mapList(crewFromApi))
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

    fun getMemberDetail(personId: String) : Either<String, Crew>{
        return try {
            val member = crew.find { it.id == personId }
            if (member != null) {
                Either.Value(member)
            } else {
                Either.Error("Crew member not found")
            }
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

}