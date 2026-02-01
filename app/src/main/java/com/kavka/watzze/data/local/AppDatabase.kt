package com.kavka.watzze.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Main Room database of the application.
 *
 * This database holds the `ColdSessionEntity` table and provides access
 * to it through the `ColdSessionDao`.
 *
 * Room generates the implementation automatically at build time.
 */
@Database(
    entities = [ColdSessionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coldSessionDao(): ColdSessionDao
}