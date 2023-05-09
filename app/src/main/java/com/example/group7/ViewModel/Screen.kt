package com.example.group7.ViewModel

sealed class Screen(val route: String) {
    object Dashboard: Screen(route = "dashboard_screen")
    object SplashScreen: Screen(route = "splashscreen_screen")
}
