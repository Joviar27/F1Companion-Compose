package com.example.f1companion.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1companion.data.TeamRepository
import com.example.f1companion.model.Team
import com.example.f1companion.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository : TeamRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Team>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Team>>
        get() = _uiState

    fun getTeamById(
        teamId : Long
    ){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getTeamByID(teamId))
        }
    }
}