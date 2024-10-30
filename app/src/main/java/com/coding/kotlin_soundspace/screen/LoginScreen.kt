package com.coding.kotlin_soundspace.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.coding.kotlin_soundspace.R


@Composable
fun LoginScreen() {



    // Handle case when user press back button
    BackHandler {
        Log.i(
            "Can't back", "No issue"
        )
    }

    Column(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Box(modifier = Modifier.weight(1f)){
            Text("Header")
        }
        Box(modifier = Modifier.weight(1f)){
            Text("SoundSpace")
        }
        Box(modifier = Modifier.weight(1f)){
            LoginArea()
        }

    }

}

@Composable
fun LoginArea() {
    // Get Context in Jetpack Compose
    val currentContext = LocalContext.current

    Box( modifier = Modifier.systemBarsPadding().padding(16.dp)){
        Column {
            Text( text = "Login Screen")

            // Login with Google button
            Box (modifier = Modifier.padding(horizontal = 20.dp)){
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    onClick = {
                        showToast(currentContext, "Login with Google")
                    }) {
                    Box(modifier = Modifier.padding (end = 10.dp) ){
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

fun showToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
@Preview
fun LoginScreenPreview(){
    LoginScreen()
}