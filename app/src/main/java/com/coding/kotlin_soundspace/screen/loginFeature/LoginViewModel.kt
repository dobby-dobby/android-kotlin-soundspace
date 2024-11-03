package com.coding.kotlin_soundspace.screen.loginFeature

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true) // Successful login
                } else {
                    onResult(false) // Login failed
                }
            }
    }

    fun checkUserLoginStatus() {
        auth.checkCurrentAuthState()
    }
}