package com.kavka.watzze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.kavka.watzze.data.local.AppDatabase
import com.kavka.watzze.data.repository.RoomColdSessionRepository
import com.kavka.watzze.data.weather.WeatherApi
import com.kavka.watzze.data.weather.WeatherRepository
import com.kavka.watzze.data.weather.WeatherRepositoryImpl
import com.kavka.watzze.ui.navigation.AppNavGraph
import com.kavka.watzze.ui.theme.WatZzeTheme
import com.kavka.watzze.viewmodel.AddEditViewModel
import com.kavka.watzze.viewmodel.HomeViewModel
import com.kavka.watzze.viewmodel.SessionsListViewModel
import com.kavka.watzze.viewmodel.WeatherViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        /**
         * Creating retrofit request on weather
         */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApi::class.java)
        val weatherRepo = WeatherRepositoryImpl(weatherApi)

        val repo = RoomColdSessionRepository(db.coldSessionDao())
        val homeVm = HomeViewModel(repo)
        val listVm = SessionsListViewModel(repo)
        val addEditVm = AddEditViewModel(repo)
        val weatherVm = WeatherViewModel(weatherRepo)


        setContent {
            WatZzeTheme {
                AppNavGraph(
                    homeViewModel = homeVm,
                    listViewModel = listVm,
                    addEditViewModel = addEditVm,
                    weatherViewModel = weatherVm
                )
            }
        }
    }
}