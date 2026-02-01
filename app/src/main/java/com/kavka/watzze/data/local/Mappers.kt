package com.kavka.watzze.data.local

import com.kavka.watzze.data.model.ColdSession

/**
 * Map from entity to model
 */
fun ColdSessionEntity.toModel() = ColdSession(
    id = id,
    dateTimeMillis = dateTimeMillis,
    durationSeconds = durationSeconds,
    waterTempC  = waterTempC,
    note = note
)

/**
 * Map from model to entity
 */
fun ColdSession.toEntity() = ColdSessionEntity(
    id = id,
    dateTimeMillis = dateTimeMillis,
    durationSeconds = durationSeconds,
    waterTempC = waterTempC,
    note = note
)