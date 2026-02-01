package com.kavka.watzze.data.weather

/**
 * Weather response from rest api
 */
data class WeatherResponseDto(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentWeatherDto?
)

/**
 * Current weather data response
 */
data class CurrentWeatherDto(
    val time: String,
    val temperature_2m: Double,
    val relative_humidity_2m: Double,
    val wind_speed_10m: Double
)