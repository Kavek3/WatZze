package com.kavka.watzze.data.repository

import kotlinx.coroutines.flow.Flow

data class ColdSession(
    val id: Long,
    val durationSeconds : Int,
    val waterTempC: Float?
)

interface ColdSessionRepository {
    fun observeSession(): Flow<List<ColdSession>>
}