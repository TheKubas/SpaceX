package cz.vlossak.spacex.ui.launchdetailscreen

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
class LaunchDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: Repository
) : ViewModel() {

    private val _viewState = MutableStateFlow(LaunchDetailScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val launchId = requireNotNull(savedStateHandle.get<String>(SpacexNavArguments.LAUNCH_ID))
        viewModelScope.launch {

            repository.getLaunchDetail(launchId).fold({ error ->
                _viewState.update {
                    LaunchDetailScreenViewState(
                        error = error,
                        loading = false
                    )
                }
            }, { details ->
                _viewState.update {
                    LaunchDetailScreenViewState(
                        data = details,
                        loading = false
                    )
                }

            })
        }

    }

}