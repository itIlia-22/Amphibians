package com.example.amphibians.repository

import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.network.AmphibiansApiService

//Репозиторий, который извлекает список
interface AmphibiansRepository {
    suspend fun getAmphibians():List<AmphibiansData>
}

//Сетевая реализация репозитория, который извлекает список
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    //Извлекае данные из ApiAmp
    override suspend fun getAmphibians(): List<AmphibiansData> = amphibiansApiService.getAmphibians()




}