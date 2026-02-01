package com.kavka.watzze.data.repository

import com.kavka.watzze.data.local.ColdSessionDao
import com.kavka.watzze.data.local.toEntity
import com.kavka.watzze.data.local.toModel
import com.kavka.watzze.data.model.ColdSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Bridge between the local persistence layer (Room) and the domain layer
 * Map Entity to Model
 */
class RoomColdSessionRepository(
    private val dao: ColdSessionDao
) : ColdSessionRepository {

    /**
     * Observes all sessions
     * @return [Flow] list of session
     */
    override fun observeSessions(): Flow<List<ColdSession>> =
        dao.observeSessions().map { list -> list.map { it.toModel() } }

    /**
     * Observes all sessions
     * @param [id] id of session
     * @return [Flow] session with the param id
     */
    override fun observeSession(id: Long): Flow<ColdSession?> =
        dao.observeSession(id).map { it?.toModel() }

    /**
     * Update or insert session into db
     * @param [session] the session what is updated or new
     */
    override suspend fun upsert(session: ColdSession) {
        if (session.id == 0L) {
            dao.insert(session.toEntity())
        } else {
            dao.update(session.toEntity())
        }
    }

    /**
     * Delete session from db
     * @param [id] id of session
     */
    override suspend fun delete(id: Long) {
        val entity = dao.observeSession(id).firstOrNull() ?: return
        dao.delete(entity)
    }
}