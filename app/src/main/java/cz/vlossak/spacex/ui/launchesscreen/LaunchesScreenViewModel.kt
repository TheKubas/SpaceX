package cz.vlossak.spacex.ui.launchesscreen

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
class LaunchesScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewState = MutableStateFlow(LaunchesScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getLaunches().fold({ error ->
                _viewState.update {
                    LaunchesScreenViewState(
                        error = error,
                        loading = false)
                }
            }, { launches ->
                _viewState.update {
                    LaunchesScreenViewState(
                        data = launches,
                        loading = false
                    )
                }

            })
        }
    }
    fun sortByName() {
        val sortedData = _viewState.value.data.sortedBy { it.name }
        _viewState.update {
            LaunchesScreenViewState(
                data = sortedData,
                loading = false
            )
        }
    }
    fun sortByDate() {
        val sortedData = _viewState.value.data.sortedBy { it.dateUtc }
        _viewState.update {
            LaunchesScreenViewState(
                data = sortedData,
                loading = false
            )
        }
    }
}