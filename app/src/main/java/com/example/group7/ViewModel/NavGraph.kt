package com.example.group7.ViewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.group7.View.Dashboard
import com.example.group7.View.SplashScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
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
            Dashboard()
        }
    }
}