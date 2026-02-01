package com.kavka.watzze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.kavka.watzze.data.local.AppDatabase
import com.kavka.watzze.data.repository.RoomColdSessionRepository
import com.kavka.watzze.ui.navigation.AppNavGraph
import com.kavka.watzze.ui.theme.WatZzeTheme
import com.kavka.watzze.viewmodel.AddEditViewModel
import com.kavka.watzze.viewmodel.HomeViewModel
import com.kavka.watzze.viewmodel.SessionsListViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /**
        * Creating database
        */
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cold_session.db"
        )
            .fallbackToDestructiveMigration(true)
            .build()

        val repo = RoomColdSessionRepository(db.coldSessionDao())
        val homeVm = HomeViewModel(repo)
        val listVm = SessionsListViewModel(repo)
        val addEditVm = AddEditViewModel(repo)

        setContent {
            WatZzeTheme {
                AppNavGraph(
                    homeViewModel = homeVm,
                    listViewModel = listVm,
                    addEditViewModel = addEditVm
                )
            }
        }
    }
}