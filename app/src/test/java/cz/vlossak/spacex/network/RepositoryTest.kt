package cz.vlossak.spacex.network

import cz.vlossak.spacex.extension.Either
import cz.vlossak.spacex.mapper.CompanyDetailsMapper
import cz.vlossak.spacex.mapper.CrewMapper
import cz.vlossak.spacex.mapper.LaunchesMapper
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RepositoryTest {

    val api = mockk<Api>()
    val companyDetailsMapper = mockk<CompanyDetailsMapper>()
    val launchesMapper = mockk<LaunchesMapper>()
    val crewMapper = mockk<CrewMapper>()

    val repository = Repository(api, companyDetailsMapper, launchesMapper, crewMapper)

    @Test
    fun memberDetailNotFound() {
        val test = repository.getMemberDetail("1")
        assertEquals(Either.Error("Crew member not found"), test)
    }

}