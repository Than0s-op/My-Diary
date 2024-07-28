package dev.than0s.mydiary.screen.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.screen.sign_in.SignInCred
import dev.than0s.mydiary.ui.theme.AppState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val emailAccountService: EmailAccountService) :
    MyDiaryViewModel() {
    val signInCred = mutableStateOf(SignInCred())

    fun onEmailChange(email: String) {
        signInCred.value = signInCred.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        signInCred.value = signInCred.value.copy(password = password)
    }

    fun onSignUpClick(restartApp: () -> Unit) {
        viewModelScope.launch {
            emailAccountService.linkAccount(
                signInCred.value.email,
                signInCred.value.password
            )
            AppState.snackbarHostState.showSnackbar("Sign Up Successfully")
            restartApp()
        }
    }
}