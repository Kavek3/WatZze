package com.kavka.watzze.data.repository

import com.kavka.watzze.data.model.ColdSession
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing cold exposure sessions
 */
interface ColdSessionRepository {

    fun observeSessions(): Flow<List<ColdSession>>

    fun observeSession(id: Long): Flow<ColdSession?>

    suspend fun upsert(session: ColdSession)

    suspend fun delete(id: Long)
}