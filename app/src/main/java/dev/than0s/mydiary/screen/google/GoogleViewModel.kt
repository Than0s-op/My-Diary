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

    private val messageShower = { message: String ->
        viewModelScope.launch {
            AppState.snackbarHostState.showSnackbar(message)
        }
        Unit
    }

    fun linkAccount(data: Intent, restartApp: () -> Unit) {
        launchCatching(messageShower) {
            googleAccountService.linkAccount(data)
            AppState.snackbarHostState.showSnackbar("Sign Up Successfully")
            restartApp()
        }
    }

    fun authenticate(data: Intent, restartApp: () -> Unit) {
        launchCatching(messageShower) {
            googleAccountService.authenticate(data)
            AppState.snackbarHostState.showSnackbar("Sign In Successfully")
            restartApp()
        }
    }
}