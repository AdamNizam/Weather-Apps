package com.example.weatherapps.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapps.data.network.retrofit.ApiConfig
import com.example.weatherapps.databinding.ActivityMainBinding
import com.example.weatherapps.repository.WeatherRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels{
        WeatherViewModelFactory(WeatherRepository(ApiConfig.getApiService()))
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weatherViewModel.fetchWeather("52.02.03.2007")

        weatherViewModel.weatherData.observe(this) { weatherResponse ->
            weatherResponse.data.forEachIndexed { _, dataItem ->
                Log.d("WeatherData", "Data item lokasi ${dataItem.lokasi}")

                binding.textViewToday.text = "${dataItem.lokasi.provinsi},  ${dataItem.lokasi.kotkab}"

                dataItem.cuaca.forEachIndexed { cuacaIndex, cuacaList ->
                    cuacaList.forEach { cuacaItem ->
                        Log.d(
                            "WeatherData",
                            "Cuaca #$cuacaIndex - Weather Desc: ${cuacaItem.weatherDesc}"
                        )
                    }
                }
            }
        }

        // Inisialisasi RecyclerView
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager



    }
}