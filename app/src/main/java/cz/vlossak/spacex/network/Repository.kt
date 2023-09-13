package cz.vlossak.spacex.network

import cz.vlossak.spacex.extension.Either
import cz.vlossak.spacex.mapper.CompanyDetailsMapper
import cz.vlossak.spacex.mapper.LaunchDetailMapper
import cz.vlossak.spacex.mapper.LaunchesMapper
import cz.vlossak.spacex.model.CompanyDetails
import cz.vlossak.spacex.model.LaunchDetail
import cz.vlossak.spacex.model.LaunchesDetail
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api,
    private val companyDetailsMapper: CompanyDetailsMapper,
    private val launchesMapper: LaunchesMapper,
    private val launchDetailMapper: LaunchDetailMapper
) {
    suspend fun getCompanyDetails(): Either<String, CompanyDetails> {
        return try {
            val companyDetails = api.getCompanyDetails()
            Either.Value(companyDetailsMapper.map(companyDetails))
        } catch (excaption: Throwable) {
            Either.Error(excaption.localizedMessage ?: "Network error")
        }
    }

    suspend fun getLaunches(): Either<String, List<LaunchesDetail>> {
        return try {
            val Launches = api.getLaunches()
            Either.Value(launchesMapper.mapList(Launches))
        } catch (excaption: Throwable) {
            Either.Error(excaption.localizedMessage ?: "Network error")
        }
    }

    suspend fun getLaunchDetail(launchId: String): Either<String, LaunchDetail> {
        return try {
            val launchDetail = api.getLaunchDetail(launchId)
            Either.Value(launchDetailMapper.map(launchDetail))
        } catch (exception: Throwable) {
            Either.Error(exception.localizedMessage ?: "Network error")
        }
    }

}