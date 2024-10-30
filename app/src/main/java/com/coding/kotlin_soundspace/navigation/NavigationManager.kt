package com.coding.kotlin_soundspace.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coding.kotlin_soundspace.screen.LoginScreen
import com.coding.kotlin_soundspace.screen.SplashScreen


@Composable
fun NavigationManager() {
    // A Navigation Controller
    val navController = rememberNavController()

    // Navigation Graph
    NavHost(navController = navController, startDestination = Screens.Splash) {
        composable<Screens.Splash> {
            SplashScreen(onNavigateToLoginScreen = {
                navController.navigate(
                    route = Screens.Login
                )
            })
        }
        composable<Screens.Login> {
            LoginScreen()
        }
    }
}