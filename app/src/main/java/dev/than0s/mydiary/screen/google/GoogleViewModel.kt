package dev.than0s.mydiary.screen.google

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.AppState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleViewModel @Inject constructor(
    private val googleAccountService: GoogleAccountService
) : MyDiaryViewModel() {

    fun linkAccount(data: Intent, restartApp: () -> Unit) {
        launchCatching(AppState.showSnackbar(viewModelScope)) {
            try {
                googleAccountService.linkAccount(data)
                AppState.snackbarHostState.showSnackbar("Sign Up Successfully")
                restartApp()
            } catch (e: Exception) {
                AppState.showSnackbar(viewModelScope).invoke(e.message ?: "Unknown Error")
            }
        }
    }

    fun authenticate(data: Intent, restartApp: () -> Unit) {
        launchCatching(AppState.showSnackbar(viewModelScope)) {
            try {
                googleAccountService.authenticate(data)
                AppState.snackbarHostState.showSnackbar("Sign In Successfully")
                restartApp()
            } catch (e: Exception) {
                AppState.showSnackbar(viewModelScope).invoke(e.message ?: "Unknown Error")
            }
        }
    }
}