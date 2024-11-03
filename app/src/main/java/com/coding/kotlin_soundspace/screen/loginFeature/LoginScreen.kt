package com.coding.kotlin_soundspace.screen.loginFeature

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coding.kotlin_soundspace.R
import com.coding.kotlin_soundspace.firebaseAuth.FbAuth


val auth = FbAuth()

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    //Get Value from Gmail and Password
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Get Context in Jetpack Compose
    val currentContext = LocalContext.current

    // Init
    LaunchedEffect(Unit) {
        loginViewModel.checkUserLoginStatus()
    }

    // Handle case when user press back button
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
            Text("SoundSpace")
        }
        Box(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.systemBarsPadding()) {

                // Two text fields for email and password
                Column {
                    Box {
                        Column {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp),
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Email") }
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp),
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                            )
                            ElevatedButton(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                onClick = {
                                    loginViewModel.loginUser(
                                        email,
                                        password,
                                        onResult = {})
                                },
                                content = {}
                            )
                        }
                    }

                    // Login with Google button
                    Box {
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            onClick = {
                                showToast(currentContext, "Login with Google")
                            }) {
                            Box(modifier = Modifier.padding(end = 10.dp)) {
                                Image(
                                    painter = painterResource(R.drawable.icon_google),
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text("Login with Google")
                        }
                    }
                }
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen()
}