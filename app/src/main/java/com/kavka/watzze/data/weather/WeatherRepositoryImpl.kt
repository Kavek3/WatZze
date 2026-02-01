package com.kavka.watzze.data.weather

/**
 * Implementation Weather repository
 */
class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    /**
     * Getting information from server
     * @param [latitude] latitude for location
     * @param [longitude] longitude for location
     */
    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherModel {
        val dto = api.getCurrentWeather(latitude, longitude)

        val current = dto.current
            ?: throw IllegalStateException("Current weather missing in response")

        return WeatherModel(
            temperatureC = current.temperature_2m,
            humidity = current.relative_humidity_2m,
            windSpeed = current.wind_speed_10m
        )
    }
}