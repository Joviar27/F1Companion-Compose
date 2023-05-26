package com.example.f1companion.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourite: Screen("favourite")
    object About : Screen("about")

    //New route with arguments
    object DetailTeam : Screen("home/{teamId}"){
        fun createRoute(teamId : Long) = "home/$teamId"
    }
}