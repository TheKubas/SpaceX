package cz.vlossak.spacex.datastore

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import cz.vlossak.spacex.model.CompanyDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private const val HOME_SCREEN_DATA_STORE = "home_screen_data_store"
private val COMPANY_NAME = stringPreferencesKey("company_name")
private val CEO = stringPreferencesKey("ceo")
private val COO = stringPreferencesKey("coo")
private val CTO = stringPreferencesKey("cto")
private val PROPULSION_CTO = stringPreferencesKey("propulsion_cto")
private val SUMMARY = stringPreferencesKey("summary")
private val WEBSITE = stringPreferencesKey("website")

@Singleton
class DataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(HOME_SCREEN_DATA_STORE)
    }

    suspend fun loadCompanyDetails(): CompanyDetails? {
        val companyName = dataStore.data.first()[COMPANY_NAME] ?: return null
        val ceo = dataStore.data.first()[CEO] ?: return null
        val coo = dataStore.data.first()[COO] ?: return null
        val cto = dataStore.data.first()[CTO] ?: return null
        val propulsionCto = dataStore.data.first()[PROPULSION_CTO] ?: return null
        val summary = dataStore.data.first()[SUMMARY] ?: return null
        val website = dataStore.data.first()[WEBSITE] ?: return null
        return CompanyDetails(
            name = companyName,
            ceo = ceo,
            coo = coo,
            cto = cto,
            cto_propulsion = propulsionCto,
            summary = summary,
            website = website
        )
    }

    suspend fun saveCompanyDetails(companyDetails: CompanyDetails) {
        dataStore.edit {
            it[COMPANY_NAME] = companyDetails.name
            it[CEO] = companyDetails.ceo
            it[COO] = companyDetails.coo
            it[CTO] = companyDetails.cto
            it[PROPULSION_CTO] = companyDetails.cto_propulsion
            it[SUMMARY] = companyDetails.summary
            it[WEBSITE] = companyDetails.website


        }
    }
}