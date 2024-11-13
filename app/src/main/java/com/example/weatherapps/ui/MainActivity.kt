package com.example.weatherapps.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import coil3.svg.SvgDecoder
import com.example.weatherapps.data.network.retrofit.ApiConfig
import com.example.weatherapps.databinding.ActivityMainBinding
import com.example.weatherapps.repository.WeatherRepository
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val weatherViewModel: WeatherViewModel by viewModels{
        WeatherViewModelFactory(WeatherRepository(ApiConfig.getApiService()))
    }
    companion object{
        const val TAG = "DataApiActivity"
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        weatherViewModel.fetchWeather("52.02.01.1001")

        weatherViewModel.lokasiData.observe(this){lokasi ->
            binding.textNameLocation.text = lokasi.kecamatan
            binding.textNameDetailLocation.text = lokasi.desa
            binding.buttonViewDetail.setOnClickListener {
                Toast.makeText(this, "Long & Lat ${lokasi.lat} ${lokasi.lon}", Toast.LENGTH_SHORT).show()
            }
        }

        weatherViewModel.cuacaData.observe(this) { cuacaList ->

            Log.d(TAG, "List Of Data Cuaca: $cuacaList")
            
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val nearestCuaca = cuacaList.minByOrNull { cuaca ->
                val cuacaDateTime = LocalDateTime.parse(cuaca.localDatetime, formatter)
                Duration.between(currentDateTime, cuacaDateTime).abs()
            }

            nearestCuaca?.let {
                binding.textInformationCuaca.text = it.weatherDesc
                binding.tvCelcius.text = "${it.T}°"
                binding.imageIconCuaca.load(it.image){
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }

            } ?: Log.d(TAG, "Data is Not Available")
        }

        weatherViewModel.isLoading.observe(this){statusLoading->
            Log.d(TAG, "status $statusLoading")


        }

        weatherViewModel.errorMessage.observe(this){statusRequest->
            Log.d(TAG, "Status Request $statusRequest ")
        }


    }
}