package com.kavka.watzze.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for accessing and modifying cold exposure sessions
 * stored in the local Room database.
 *
 * This interface defines all database operations related to `ColdSessionEntity`,
 * including observing changes, inserting new sessions, updating existing ones
 * and deleting sessions.
 */
@Dao
interface ColdSessionDao {

    /**
     * Observes all cold sessions sorted by their timestamp in descending order.
     * @return A [Flow] that emits a new list whenever the table changes.
     */
    @Query("SELECT * FROM cold_session ORDER BY dateTimeMillis DESC")
    fun observeSessions(): Flow<List<ColdSessionEntity>>

    /**
     * Observes a single cold session by its ID.
     * @param id Unique identifier of the session.
     * @return A [Flow] that emits the session or `null` if not found.
     */
    @Query("SELECT * FROM cold_session WHERE id = :id")
    fun observeSession(id: Long): Flow<ColdSessionEntity?>

    /**
     * Inserts a new cold session into the database.
     * @param session The session to insert.
     * @return The newly generated row ID.
     */
    @Insert
    suspend fun insert(session: ColdSessionEntity): Long

    /**
     * Updates an existing cold session in the database.
     * @param session The updated session entity.
     */
    @Update
    suspend fun update(session: ColdSessionEntity)

    /**
     * Deletes a cold session from the database.
     * @param session The session to remove.
     */
    @Delete
    suspend fun delete(session: ColdSessionEntity)
}