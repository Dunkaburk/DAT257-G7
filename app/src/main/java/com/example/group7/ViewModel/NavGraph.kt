package com.example.group7.ViewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.group7.View.*

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ){
       /* composable(
            Screen.SplashScreen.route
        ){
            SplashScreen()
        } */
        composable(
            Screen.Dashboard.route
        ){
            DashboardContent(navController = navController)
        }
        composable(
            Screen.StepsPanel.route
        ){
            StepsContent(navController = navController)
        }
    }
}