package com.example.weatherapps.data.network.retrofit

import com.example.weatherapps.data.network.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("prakiraan-cuaca")
    fun getWeatherForecast(
        @Query("adm4") adm4: String
    ): Call<WeatherResponse>
}