package dev.than0s.mydiary.screen.google

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.than0s.mydiary.R
import dev.than0s.mydiary.SPLASH_SCREEN
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.ui.theme.AppState
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class GoogleViewModel @Inject constructor(
    private val googleAccountService: GoogleAccountService
) : MyDiaryViewModel() {

    fun linkAccount(data: Intent, restartApp: () -> Unit) {
        viewModelScope.launch {
            try {
                googleAccountService.linkAccount(data)
                AppState.snackbarHostState.showSnackbar("Sign Up Successfully")
                restartApp()
            } catch (e: FirebaseAuthUserCollisionException) {
                AppState.snackbarHostState.showSnackbar(e.message ?: "Account already present")
            } catch (e: Exception) {
                AppState.snackbarHostState.showSnackbar(e.message ?: "Unknown error")
            }
        }
    }

    fun authenticate(data: Intent, restartApp: () -> Unit) {
        viewModelScope.launch {
            try {
                googleAccountService.authenticate(data)
                AppState.snackbarHostState.showSnackbar("Sign In Successfully")
                restartApp()
            } catch (e: FirebaseAuthUserCollisionException) {
                AppState.snackbarHostState.showSnackbar(e.message ?: "Account already present")
            } catch (e: Exception) {
                AppState.snackbarHostState.showSnackbar(e.message ?: "Unknown error")
            }
        }
    }
}