package com.kavka.watzze.data.weather

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * WeatherApi interface
 */
interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") units: String = "temperature_2m,relative_humidity_2m,wind_speed_10m",
        @Query("timezone") lang: String = "auto"
    ): WeatherResponseDto
}