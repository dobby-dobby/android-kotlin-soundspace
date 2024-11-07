package com.coding.kotlin_soundspace.firebaseAuth

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class FbAuth {
    // Declare an instance of FirebaseAuth
    private lateinit var auth: FirebaseAuth

    // Initialize the FirebaseAuth instance
    fun checkCurrentAuthState() {
        auth = Firebase.auth
    }

    
}