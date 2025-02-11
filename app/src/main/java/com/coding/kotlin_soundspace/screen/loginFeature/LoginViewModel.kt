package com.coding.kotlin_soundspace.screen.loginFeature

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.kotlin_soundspace.service.model.AccountService
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountService: AccountService) : ViewModel() {

    private var _navigationEvent = MutableLiveData<String?>()
    val navigationEvent: LiveData<String?> = _navigationEvent

    fun onNavigationHandled() {
        _navigationEvent.value = null  // Reset the event after handling
    }

    private fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("Error Tag", throwable.message.orEmpty())
            },
            block = block
        )

    fun onSignUpWithGoogle(credential: Credential) {
        launchCatching {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                _navigationEvent.value = "HomeScreen"
            } else {
                Log.e("Error Tag", "Issue")
            }
        }
    }
}