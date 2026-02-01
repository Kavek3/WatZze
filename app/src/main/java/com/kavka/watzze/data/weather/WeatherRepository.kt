package com.kavka.watzze.data.weather

/**
 * Weather repository
 */
interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherModel
}