package com.example.weatherapps.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapps.data.network.response.WeatherResponse
import com.example.weatherapps.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel (private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    fun fetchWeather(adm4: String) {
        viewModelScope.launch {
            try {
                val response = weatherRepository.getAllWeather(adm4)
                Log.d("WeatherData", "Data fetched successfully: $response")
                _weatherData.value = response
            } catch (e: Exception) {
                Log.e("WeatherData", "Error fetching data: ${e.message}", e)
            }
        }
    }

}