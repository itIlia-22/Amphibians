package com.example.amphibians.repository

import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.network.AmphibiansApiService

//Репозиторий, который извлекает список
interface AmphibiansRepository {
    suspend fun getAmphibians(): AmphibiansData
}

//Сетевая реализация репозитория, который извлекает список
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): AmphibiansData {
        //Извлекае данные из ApiAmp
        return amphibiansApiService.getAmphibians()
    }

}