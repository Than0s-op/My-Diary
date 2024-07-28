package dev.than0s.mydiary.screen.sign_in

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.screen.settings.User
import dev.than0s.mydiary.ui.theme.AppState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val emailAccountService: EmailAccountService) :
    MyDiaryViewModel() {
    val signInCred = mutableStateOf(SignInCred())

    fun onEmailChange(email: String) {
        signInCred.value = signInCred.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        signInCred.value = signInCred.value.copy(password = password)
    }

    fun onSignInClick(restartApp: () -> Unit) {
        viewModelScope.launch {
            emailAccountService.authenticate(
                signInCred.value.email,
                signInCred.value.password
            )
            AppState.snackbarHostState.showSnackbar("Sign In Successfully")
            restartApp()
        }
    }
}