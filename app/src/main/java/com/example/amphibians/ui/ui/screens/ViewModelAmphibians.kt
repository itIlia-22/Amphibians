package com.example.amphibians.ui.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.repository.AmphibiansRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//UI State HomeScreen
sealed interface AmphibiansUiState {
    data class Success(val amphibiansData: List<AmphibiansData>) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState

}

class ViewModelAmphibians(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    //Изменяемое состояние, в котором хранится статус самого последнего запроса
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    //отображения статуса
    init {
        getAmphibians()
    }

    /**
     * Получает информацию из сервиса модернизации и обновляет
     */
     fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(
                    amphibiansRepository.getAmphibians()

                )
            } catch (e: IOException) {
                AmphibiansUiState.Error

            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    /**
     * Фабрика для [ViewModelAmphibians], которая принимает [AmphibiansRepository] в качестве зависимости
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                ViewModelAmphibians(amphibiansRepository = amphibiansRepository)
            }

        }
    }
}