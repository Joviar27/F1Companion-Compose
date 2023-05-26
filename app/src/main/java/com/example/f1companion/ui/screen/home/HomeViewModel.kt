package com.example.f1companion.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1companion.data.TeamRepository
import com.example.f1companion.model.Team
import com.example.f1companion.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TeamRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<Team>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Team>>>
        get() = _uiState

    fun getAllTeam() {
        viewModelScope.launch {
            repository.getAllTeam()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { teams ->
                    _uiState.value = UiState.Success(teams)
                }
        }
    }

    fun updateFavorite(id : Long){
        viewModelScope.launch {
            repository.updateFavorite(id)
        }
    }

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery : String){
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchTeam(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { teams ->
                    _uiState.value = UiState.Success(teams)
                }
        }
    }

}