package com.coding.kotlin_soundspace.screen.loginFeature

import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import com.coding.kotlin_soundspace.R
import com.coding.kotlin_soundspace.ui.theme.fontFamily
import com.coding.kotlin_soundspace.ui.theme.loginAppNameFont
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigationToHomeScreen : () -> Unit
) {

    // Observe Navigation from ViewModel
    val navigationEvent by loginViewModel.navigationEvent.observeAsState()

    navigationEvent?.let {
        onNavigationToHomeScreen()
        loginViewModel.onNavigationHandled()
    }
    
    // CoroutineScope
    val coroutineScope = rememberCoroutineScope()

    // Get Context in Jetpack Compose
    val currentContext = LocalContext.current

    // Init
    LaunchedEffect(Unit) {
        launchCredManBottomSheet(currentContext) { result ->
            loginViewModel.onSignUpWithGoogle(result)
        }
    }

    BackHandler {
        Log.i(
            "Can't back", "No issue"
        )
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp)
        .imePadding()) {
        Box(modifier = Modifier.weight(1f)) {
            Text("Header")
        }
        Box(modifier = Modifier.weight(1f)) {
            Text("Sound Space", fontFamily = loginAppNameFont)
        }
        Box(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.systemBarsPadding()) {

                // Two text fields for email and password
                Column {

                    // Login with Google button
                    Box {
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            onClick = {
                                coroutineScope.launch { coroutineScope.launch { launchCredManButtonUI(currentContext) { credential ->
                                    loginViewModel.onSignUpWithGoogle(
                                        credential
                                    )
                                }
                                } }
                            }) {
                            Box(modifier = Modifier.padding(end = 10.dp)) {
                                Image(
                                    painter = painterResource(R.drawable.icon_google),
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text("Login with Google", fontFamily = fontFamily)
                        }
                    }
                }
            }
        }
    }
}

private suspend fun launchCredManButtonUI(
    context: Context,
    hasFilter: Boolean = true,
    onRequestResult: (Credential) -> Unit
) {
    try {
        val signInWithGoogleOption = GetSignInWithGoogleOption
            .Builder("143823216192-d7ugvrf57ujovjs8h9rkbtccmlmtfe3n.apps.googleusercontent.com")
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        val result = CredentialManager.create(context).getCredential(
            request = request,
            context = context
        )

        onRequestResult(result.credential)
    } catch (e: NoCredentialException) {
        Log.d("Error Tag", e.message.orEmpty())
        if (hasFilter) {
            launchCredManBottomSheet(context, hasFilter = false, onRequestResult)
        }
    } catch (e: GetCredentialException) {
        Log.d("Error Tag", e.message.orEmpty())
    }
}

suspend fun launchCredManBottomSheet(
    context: Context,
    hasFilter: Boolean = true,
    onRequestResult: (Credential) -> Unit
) {
    try {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(hasFilter)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = CredentialManager.create(context).getCredential(
            request = request,
            context = context
        )

        onRequestResult(result.credential)
    } catch (e: NoCredentialException) {
        Log.d("Error Tag", e.message.orEmpty())

        //If the bottom sheet was launched with filter by authorized accounts, we launch it again
        //without filter so the user can see all available accounts, not only the ones that have
        //been previously authorized in this app
        if (hasFilter) {
            launchCredManBottomSheet(context, hasFilter = false, onRequestResult)
        }
    } catch (e: GetCredentialException) {
        Log.d("Error Tag", e.message.orEmpty())
    }
}