package com.coding.kotlin_soundspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coding.kotlin_soundspace.navigation.NavigationManager
import com.coding.kotlin_soundspace.ui.theme.SnackbarManager
import com.coding.kotlin_soundspace.ui.theme.SoundSpaceAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState()}
            val appState = rememberAppState(snackBarHostState)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.systemBarsPadding()){
                    NavigationManager(appState)
                }
            }

        }
    }

    @Composable
    fun rememberAppState(
        snackBarHostState: SnackbarHostState,
        navController: NavHostController = rememberNavController(),
        snackBarManager: SnackbarManager = SnackbarManager,
        coroutineScope: CoroutineScope = rememberCoroutineScope()
    ): SoundSpaceAppState {
        return remember(snackBarHostState, navController, snackBarManager, coroutineScope) {
            SoundSpaceAppState(snackBarHostState, navController, snackBarManager, coroutineScope)
        }
    }
}