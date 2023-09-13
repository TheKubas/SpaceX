package cz.vlossak.spacex.ui.companyscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val repository: Repository
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {

        viewModelScope.launch {

            repository.getCompanyDetails().fold({ error ->
                _viewState.update {
                    HomeScreenViewState(
                        error = error,
                        loading = false
                    )
                }

            }, { details ->
                _viewState.update {
                    HomeScreenViewState(
                        data = details,
                        loading = false
                    )
                }
            })
        }


    }
}