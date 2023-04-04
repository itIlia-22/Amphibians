package com.example.amphibians.ui.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.amphibians.model.AmphibiansData

//UI State HomeScreen
sealed interface AmphibiansUiState {
    data class Success(val amphibiansData: AmphibiansData) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState

}

class ViewModelAmphibians() : ViewModel() {
    //Изменяемое состояние, в котором хранится статус самого последнего запроса
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

}