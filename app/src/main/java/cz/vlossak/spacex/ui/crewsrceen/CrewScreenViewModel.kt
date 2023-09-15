package cz.vlossak.spacex.ui.crewsrceen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vlossak.spacex.extension.fold
import cz.vlossak.spacex.model.Crew
import cz.vlossak.spacex.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _viewState = MutableStateFlow(CrewScreenViewState())
    val viewState = _viewState.asStateFlow()

    private var defaultSortedData: List<Crew> = emptyList()

    init {
        viewModelScope.launch {
            repository.getCrew().fold({ error ->
                _viewState.update {
                    CrewScreenViewState(
                        error = error,
                        loading = false
                    )
                }
            }, { launches ->
                _viewState.update {
                    CrewScreenViewState(
                        data = launches,
                        loading = false
                    )
                }

            })
        }
    }

    fun sortByName() {
        val sortedData = _viewState.value.data.sortedBy { it.name }
        defaultSortedData = _viewState.value.data
        _viewState.update {
            CrewScreenViewState(
                data = sortedData,
                loading = false
            )
        }
    }

    fun sortByDefault() {
        if (defaultSortedData.isNotEmpty()) {
            _viewState.update {
                CrewScreenViewState(
                    data = defaultSortedData,
                    loading = false
                )
            }
        }
    }
}