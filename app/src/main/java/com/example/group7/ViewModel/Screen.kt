package com.example.group7.ViewModel

sealed class Screen(val route: String) {
    object Dashboard: Screen(route = "dashboard_screen")
    object SplashScreen: Screen(route = "splashscreen_screen")

    object StepsPanel: Screen(route = "stepspanel_screen")

    object WaterPanel: Screen(route = "waterpanel_screen")


}
