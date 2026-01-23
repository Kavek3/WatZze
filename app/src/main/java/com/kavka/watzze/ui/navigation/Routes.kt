package com.kavka.watzze.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val LIST = "list"
    const val ADD_EDIT = "add_edit"
    const val DETAIL = "detail/{id}"
    const val WEATHER = "weather"

    fun detail(id: Int) = "detail/$id"
}