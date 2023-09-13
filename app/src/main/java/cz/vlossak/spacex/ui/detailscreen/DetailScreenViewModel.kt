package cz.vlossak.spacex.ui.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vlossak.spacex.extension.fold
import cz.vlossak.spacex.network.Repository
import cz.vlossak.spacex.ui.navigation.SpacexNavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: Repository
) : ViewModel() {

    private val _viewState = MutableStateFlow(DetailScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val launchId = requireNotNull(savedStateHandle.get<String>(SpacexNavArguments.LAUNCH_ID))
        viewModelScope.launch {

            repository.getLaunchDetail(launchId).fold({ error ->
                _viewState.update {
                    DetailScreenViewState(
                        error = error,
                        loading = false
                    )
                }
            }, { details ->
                _viewState.update {
                    DetailScreenViewState(
                        data = details,
                        loading = false
                    )
                }

            })
        }

    }

}