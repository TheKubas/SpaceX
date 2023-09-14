package cz.vlossak.spacex.ui.companyscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vlossak.spacex.datastore.DataStore
import cz.vlossak.spacex.extension.fold
import cz.vlossak.spacex.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyDetailsScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStore: DataStore
) : ViewModel() {

    private val _viewState = MutableStateFlow(CompanyDetailViewState())
    val viewState = _viewState.asStateFlow()

    init {

        viewModelScope.launch {
            val storedCompanyDetails = dataStore.loadCompanyDetails()

            if (storedCompanyDetails != null) {
                _viewState.update {
                    CompanyDetailViewState(
                        data = storedCompanyDetails,
                        loading = false
                    )
                }
                Log.d("CompanyDetailsViewModel", "Company details loaded from data preferences")
            }

            repository.getCompanyDetails().fold({ error ->
                _viewState.update {
                    CompanyDetailViewState(
                        error = error,
                        loading = false
                    )
                }

            }, { companyDetails ->
                _viewState.update {
                    CompanyDetailViewState(
                        data = companyDetails,
                        loading = false
                    )
                }
                if (companyDetails != storedCompanyDetails) {
                    dataStore.saveCompanyDetails(companyDetails)
                    Log.d("CompanyDetailsViewModel", "Company details saved to data preferences")
                }
            })
        }


    }
}