package com.example.weatherapps.repository

import com.example.weatherapps.data.network.response.WeatherResponse
import com.example.weatherapps.data.network.retrofit.ApiService
import retrofit2.await

class WeatherRepository(
    private val apiService: ApiService
) {
    suspend fun getAllWeather(adm4: String): WeatherResponse{
        return apiService.getWeatherForecast(adm4).await()
    }

}