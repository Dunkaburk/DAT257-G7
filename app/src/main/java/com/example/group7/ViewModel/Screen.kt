package com.example.group7.ViewModel

sealed class Screen(val route: String) {
    object Dashboard: Screen(route = "dashboard_screen")
    object MainActivity: Screen(route = "mainactivity_screen")
    object StartScreen: Screen(route = "startscreen_screen")
}
