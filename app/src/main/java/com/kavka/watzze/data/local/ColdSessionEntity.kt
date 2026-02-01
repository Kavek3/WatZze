package com.kavka.watzze.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a cold exposure session stored in the local database.
 *
 * Each record contains the session timestamp, duration, water temperature
 * and an optional user note.
 */
@Entity(tableName = "cold_session")
data class ColdSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long= 0,
    val dateTimeMillis: Long,
    val durationSeconds: Int,
    val waterTempC: Float?,
    val note: String?
)