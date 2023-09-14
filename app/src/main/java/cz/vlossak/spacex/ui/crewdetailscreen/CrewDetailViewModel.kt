package cz.vlossak.spacex.ui.crewdetailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vlossak.spacex.extension.fold
import cz.vlossak.spacex.network.Repository
import cz.vlossak.spacex.ui.launchdetailscreen.LaunchDetailScreenViewState
import cz.vlossak.spacex.ui.navigation.SpacexNavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: Repository
) : ViewModel() {
    private val _viewState = MutableStateFlow(CrewDetailViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val personId = requireNotNull(savedStateHandle.get<String>(SpacexNavArguments.PERSON_ID))
        viewModelScope.launch {

            repository.getPersonDetail(personId).fold({ error ->
                _viewState.update {
                    CrewDetailViewState(
                        error = error,
                        loading = false
                    )
                }
            }, { details ->
                _viewState.update {
                    CrewDetailViewState(
                        data = details,
                        loading = false
                    )
                }

            })
        }
    }
}