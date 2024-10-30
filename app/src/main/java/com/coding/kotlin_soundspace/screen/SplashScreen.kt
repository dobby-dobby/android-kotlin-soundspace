package com.coding.kotlin_soundspace.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Splash Screen", modifier = Modifier.align(Alignment.Center))
    }

   Button( onClick = onNavigateToLoginScreen) {
       Text("Navigation To Login")
   }
}