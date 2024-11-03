package com.coding.kotlin_soundspace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coding.kotlin_soundspace.screen.HomeScreen
import com.coding.kotlin_soundspace.screen.SplashScreen
import com.coding.kotlin_soundspace.screen.loginFeature.LoginScreen


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
        composable<Screens.Home> {
            HomeScreen()
        }
    }
}