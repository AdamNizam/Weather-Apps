package com.example.weatherapps.data.network.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("lokasi")
	val lokasi: Lokasi
)

data class DataItem(

	@field:SerializedName("cuaca")
	val cuaca: List<List<CuacaItemItem>>,

	@field:SerializedName("lokasi")
	val lokasi: Lokasi
)

data class Lokasi(

	@field:SerializedName("provinsi")
	val provinsi: String,

	@field:SerializedName("desa")
	val desa: String,

	@field:SerializedName("kota")
	val kota: String,

	@field:SerializedName("adm3")
	val adm3: String,

	@field:SerializedName("adm2")
	val adm2: String,

	@field:SerializedName("timezone")
	val timezone: String,

	@field:SerializedName("adm4")
	val adm4: String,

	@field:SerializedName("kecamatan")
	val kecamatan: String,

	@field:SerializedName("adm1")
	val adm1: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("kotkab")
	val kotkab: String,

	@field:SerializedName("type")
	val type: String
)

data class CuacaItemItem(
	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("vs_text")
	val vsText: String,

	@field:SerializedName("analysis_date")
	val analysisDate: String,

	@field:SerializedName("time_index")
	val timeIndex: String,

	@field:SerializedName("local_datetime")
	val localDatetime: String,

	@field:SerializedName("wd_deg")
	val wdDeg: Int,

	@field:SerializedName("wd")
	val wd: String,

	@field:SerializedName("hu")
	val hu: Int,

	@field:SerializedName("utc_datetime")
	val utcDatetime: String,

	@field:SerializedName("datetime")
	val datetime: String,

	@field:SerializedName("t")
	val T: Int,

	@field:SerializedName("tcc")
	val tcc: Int,

	@field:SerializedName("weather_desc_en")
	val weatherDescEn: String,

	@field:SerializedName("wd_to")
	val wdTo: String,

	@field:SerializedName("weather")
	val weather: Int,

	@field:SerializedName("weather_desc")
	val weatherDesc: String,

	@field:SerializedName("tp")
	val tp: Double,

	@field:SerializedName("ws")
	val ws: Double,

	@field:SerializedName("vs")
	val vs: Int
)

