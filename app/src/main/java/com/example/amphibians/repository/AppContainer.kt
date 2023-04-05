package com.example.amphibians.repository

import retrofit2.Retrofit
import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

//Контейнер для внедрения зависимостей на уровне приложения.
interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}
/*
Реализация контейнера для внедрения зависимостей на уровне приложения.
 */

class DefaultAppContainer : AppContainer {

    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    //Retrofit builder для создания объекта retrofit с помощью конвертера
    @kotlinx.serialization.ExperimentalSerializationApi
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    //Модифицированный объект сервиса для создания вызовов api
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    //Реализация DI для хранилища
    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitService)
    }



}