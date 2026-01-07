package com.kavka.watzze.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeColdSessionRepository : ColdSessionRepository  {
    override fun observeSession(): Flow<List<ColdSession>> =
        flowOf(
            listOf(
                ColdSession(
                    id = 1,
                    durationSeconds = 120,
                    watterTempC = 10.5f
                ),
                ColdSession(
                    id = 2,
                    durationSeconds = 60,
                    watterTempC = 9f
                )
            )
        )
}