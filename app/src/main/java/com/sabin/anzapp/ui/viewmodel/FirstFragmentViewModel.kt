package com.sabin.anzapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabin.anzapp.data.model.SpaceXLaunchesModel
import com.sabin.anzapp.data.repository.SpaceXLaunchesRepository
import com.sabin.anzapp.ui.base.UiState
import com.sabin.anzapp.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val spaceXLaunchesRepository: SpaceXLaunchesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<SpaceXLaunchesModel>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<SpaceXLaunchesModel>>> = _uiState

    init {
        fetchAllLaunches()
    }

    /**
     * Fetches all launches from the SpaceXLaunchesRepository and updates the UI state accordingly.
     */
    fun fetchAllLaunches() {
        viewModelScope.launch {
            spaceXLaunchesRepository.getLaunches(AppConstant.LIMIT)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}