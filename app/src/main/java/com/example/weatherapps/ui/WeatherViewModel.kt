package com.example.weatherapps.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapps.data.network.response.CuacaItemItem
import com.example.weatherapps.data.network.response.Lokasi
import com.example.weatherapps.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _cuacaData = MutableLiveData<List<CuacaItemItem>>()
    val cuacaData: LiveData<List<CuacaItemItem>> get() = _cuacaData

    private val _lokasiData = MutableLiveData<Lokasi>()
    val lokasiData: LiveData<Lokasi> get() = _lokasiData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: MutableLiveData<String?> get() = _errorMessage

    fun fetchWeather(adm4: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = weatherRepository.getAllWeather(adm4)
                Log.d("WeatherData", "Data fetched successfully: $response")

                _lokasiData.value = response.lokasi
                val flattenedList = response.data.flatMap { dataItem -> dataItem.cuaca.flatten() }
                _cuacaData.value = flattenedList

                _errorMessage.value = null
            } catch (e: Exception) {
                Log.e("WeatherData", "Error fetching data: ${e.message}", e)
                _errorMessage.value = "Error fetching data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

