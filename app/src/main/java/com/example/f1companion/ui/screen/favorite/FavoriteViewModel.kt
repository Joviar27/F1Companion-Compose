package com.example.f1companion.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1companion.data.TeamRepository
import com.example.f1companion.model.Team
import com.example.f1companion.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: TeamRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<Team>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Team>>>
        get() = _uiState

    fun getAllFavorite() {
        viewModelScope.launch {
            repository.getAllFavorite()
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

}