package com.kavka.watzze.data.model

/**
 * Domain model for session
 */
data class ColdSession (
    val id: Long = 0L,
    val dateTimeMillis: Long = System.currentTimeMillis(),
    val durationSeconds: Int,
    val waterTempC: Float?,
    val note: String?
)