package dev.than0s.mydiary.screen.sign_up

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.screen.sign_in.SignInCred
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
        try {
            launchCatching(ScaffoldState::showSnackBar) {
                emailAccountService.linkAccount(
                    signInCred.value.email,
                    signInCred.value.password
                )
                ScaffoldState.showSnackBar("Sign Up Successfully")
                restartApp()
            }
        } catch (e: Exception) {
            ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
        }
    }
}